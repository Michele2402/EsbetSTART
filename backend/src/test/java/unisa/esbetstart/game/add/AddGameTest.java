package unisa.esbetstart.game.add;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AddGameTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void validRequest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/games/add")
                        .contentType("application/json")
                        .content(AddGameJsonPayloads.VALID_ADD_GAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void nullName() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/games/add")
                        .contentType("application/json")
                        .content(AddGameJsonPayloads.INVALID_GAME_NAME_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Name is null or empty"),
                "Expected error message to mention 'Name is null or empty'");
    }

    @Test
    void emptyRules() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/games/add")
                        .contentType("application/json")
                        .content(AddGameJsonPayloads.EMPTY_RULES_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("AddGameRequest has no rules"),
                "Expected error message to mention 'AddGameRequest has no rules'");
    }

    @Test
    void EmptyRuleName() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/games/add")
                        .contentType("application/json")
                        .content(AddGameJsonPayloads.INVALID_RULE_NAME_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Rule name is null or empty"),
                "Expected error message to mention 'Rule name is null or empty'");
    }

    @Test
    void NegativeRulePosition() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/games/add")
                        .contentType("application/json")
                        .content(AddGameJsonPayloads.INVALID_RULE_POSITION_REQUEST)
        ).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Rule position is null or negative"),
                "Expected error message to mention 'Rule position is null or negative'");
    }
}
