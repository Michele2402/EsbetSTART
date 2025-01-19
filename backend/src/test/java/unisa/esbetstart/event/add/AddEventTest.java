package unisa.esbetstart.event.add;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.RuleEntity;
import unisa.esbetstart.eventmanagement.infrastructure.repository.CompetitionJpaRepository;
import unisa.esbetstart.eventmanagement.infrastructure.repository.GameJpaRepository;

import java.util.Set;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AddEventTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private CompetitionJpaRepository competitionJpaRepository;

    private void databaseSetup() {
        GameEntity game = GameEntity.builder()
                .id(UUID.randomUUID())
                .name("Test Game")
                .build();

        RuleEntity win = RuleEntity.builder()
                .id(UUID.randomUUID())
                .name("Win")
                .game(game)
                .build();

        RuleEntity lose = RuleEntity.builder()
                .id(UUID.randomUUID())
                .name("Lose")
                .game(game)
                .build();

        game.setRules(Set.of(win, lose));

        gameJpaRepository.save(game);

        CompetitionEntity competition = CompetitionEntity.builder()
                .id(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
                .name("Test Competition")
                .game(game)
                .build();
        competitionJpaRepository.save(competition);
    }

    @Test
    void validRequest() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.VALID_ADD_EVENT_REQUEST)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void nullCompetitionId() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.NULL_COMPETITION_ID_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for null competition ID");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Competition Id in event is null or empty"),
                "Expected error message to mention 'Competition Id in event is null or empty'");
    }

    @Test
    void invalidCompetitionId() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.INVALID_COMPETITION_ID_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for invalid competition ID");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Competition Id in event is not a valid UUID"),
                "Expected error message to mention 'Competition Id in event is not a valid UUID'");
    }

    @Test
    void wrongCompetitionId() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.WRONG_COMPETITION_ID_REQUEST)
        ).andReturn();

        Assertions.assertEquals(404, result.getResponse().getStatus(),
                "Expected HTTP status 404 for wrong competition ID");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Competition with id f47ac10b-58cc-4372-a567-0e02b2c3d478 not found"),
                "Expected error message to mention 'Competition with id f47ac10b-58cc-4372-a567-0e02b2c3d478 not found'");
    }

    @Test
    void nullName() throws Exception {
        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.NULL_NAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for null name");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Name of the event is null or empty"),
                "Expected error message to mention 'Name of the event is null or empty'");
    }

    @Test
    void pastDate() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.INVALID_DATE_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for a past event date");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Date of the event is null or in the past"),
                "Expected error message to mention 'Date of the event is null or in the past'");
    }

    @Test
    void nullOddName() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.NULL_ODD_NAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for null odd");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Odd name is null or empty"),
                "Expected error message to mention 'Odd name is null or empty'");
    }

    @Test
    void negativeOddValue() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.NEGATIVE_ODD_VALUE_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for negative odd value");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Odd value is null or negative"),
                "Expected error message to mention 'Odd value is null or negative'");
    }

    @Test
    void oddSizeMismatch() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.ODD_SIZE_MISMATCH_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 400 for odd and rule size mismatch");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("The number of rules and odds must be the same"),
                "Expected error message to mention 'The number of rules and odds must be the same'");
    }

    @Test
    void oddNameMismatch() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.ODD_NAME_MISMATCH_REQUEST)
        ).andReturn();

        Assertions.assertEquals(404, result.getResponse().getStatus(),
                "Expected HTTP status 400 for odd and rule name mismatch");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Rule with name Draw not found"),
                "Expected error message to mention 'Rule with name Draw not found'");
    }

    @Test
    void duplicateOddName() throws Exception {

        databaseSetup();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .contentType("application/json")
                        .content(AddEventJsonPayloads.DUPLICATE_ODD_NAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(406, result.getResponse().getStatus(),
                "Expected HTTP status 406 for duplicate odd name");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("The odd names must be unique"),
                "Expected error message to mention 'The odd names must be unique'");
    }
}
