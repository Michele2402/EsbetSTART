package unisa.esbetstart.transaction.offer.add;
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
public class AddOfferTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void validRequest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.VALID_ADD_OFFER_REQUEST)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus(),
                "Expected status code 200 for a valid request");
    }

    @Test
    void nullName() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.INVALID_NAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected status code 400 when name is null");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Name is null or empty"),
                "Expected error message to mention 'Name is null or empty'");
    }

    @Test
    void nullDescription() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.INVALID_DESCRIPTION_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected status code 400 when description is null");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Description is null or empty"),
                "Expected error message to mention 'Description is null or empty'");
    }

    @Test
    void invalidGoal() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.INVALID_GOAL_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected status code 400 when goal is negative");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Goal is null or negative"),
                "Expected error message to mention 'Goal is null or negative'");
    }

    @Test
    void invalidPrice() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.INVALID_PRICE_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected status code 400 when price is negative");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Price is null or negative"),
                "Expected error message to mention 'Price is null or negative'");
    }

    @Test
    void invalidExpirationDate() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.INVALID_EXPIRATION_DATE_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected status code 400 when expiration date is invalid");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Expiration date is not a valid date"),
                "Expected error message to mention 'Expiration date is not a valid date'");
    }

    @Test
    void invalidOfferType() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/offers/add")
                        .contentType("application/json")
                        .content(AddOfferJsonPayloads.INVALID_OFFER_TYPE_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected status code 400 when offer type is invalid");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Offer type is not a valid OfferType"),
                "Expected error message to mention 'Offer type is not a valid OfferType'");
    }
}