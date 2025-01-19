package unisa.esbetstart.competition.add;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.repository.GameJpaRepository;

import java.util.Map;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AddCompetitionTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Test
    void validRequest() throws Exception {

        GameEntity game = GameEntity.builder()
                .id(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
                .name("test game")
                .build();
        gameJpaRepository.save(game);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/competitions/add")
                        .contentType("application/json")
                        .content(AddCompetitionJsonPayloads.VALID_ADD_COMPETITION_REQUEST)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void nullGameId() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/competitions/add")
                        .contentType("application/json")
                        .content(AddCompetitionJsonPayloads.INVALID_GAME_ID_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Game Id is null or empty"),
                "Expected error message to mention 'Game Id is null or empty'");
    }

    @Test
    void nullName() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/competitions/add")
                        .contentType("application/json")
                        .content(AddCompetitionJsonPayloads.INVALID_COMPETITION_NAME_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Competition Name is null or empty"),
                "Expected error message to mention 'Competition Name is null or empty'");
    }

    @Test
    void nullOriginCountry() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/competitions/add")
                        .contentType("application/json")
                        .content(AddCompetitionJsonPayloads.INVALID_ORIGIN_COUNTRY_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Competition Origin Country is null or empty"),
                "Expected error message to mention 'Competition Origin Country is null or empty'");
    }

    @Test
    void invalidGameId() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/competitions/add")
                        .contentType("application/json")
                        .content(AddCompetitionJsonPayloads.INVALID_GAME_ID_FORMAT_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Game Id is not a valid UUID"),
                "Expected error message to mention 'Game Id is not a valid UUID'");
    }

    @Test
    void wrongGameId() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/competitions/add")
                        .contentType("application/json")
                        .content(AddCompetitionJsonPayloads.WRONG_GAME_ID)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Game with id f47ac10b-58cc-4372-a567-0e02b2c3d478 not found"),
                "Expected error message to mention 'Game with id f47ac10b-58cc-4372-a567-0e02b2c3d478 not found'");
    }
}
