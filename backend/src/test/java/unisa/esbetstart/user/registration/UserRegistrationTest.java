package unisa.esbetstart.user.registration;

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
public class UserRegistrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void validRegistrationRequest() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.VALID_REGISTER_REQUEST)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus(),
                "Expected HTTP status 200 for valid registration request");
    }

    @Test
    void nullName() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.NULL_NAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 406 for null name request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Name is null or empty"),
                "Expected error message to mention 'Name is null or empty'");
    }

    @Test
    void nullSurname() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.NULL_SURNAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 406 for null surname request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Surname is null or empty"),
                "Expected error message to mention 'Surname is null or empty'");
    }

    @Test
    void nullEmail() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.NULL_EMAIL_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 406 for null email request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Email is null or empty"),
                "Expected error message to mention 'Email is null or empty'");
    }

    @Test
    void nullUsername() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.NULL_USERNAME_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 406 for null username request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Username is null or empty"),
                "Expected error message to mention 'Username is null or empty'");
    }

    @Test
    void nullPassword() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.NULL_PASSWORD_REQUEST)
        ).andReturn();

        Assertions.assertEquals(400, result.getResponse().getStatus(),
                "Expected HTTP status 406 for null password request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Password is null or empty"),
                "Expected error message to mention 'Password is null or empty'");
    }

    @Test
    void usernameTooLong() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.USERNAME_TOO_LONG_REQUEST)
        ).andReturn();

        Assertions.assertEquals(406, result.getResponse().getStatus(),
                "Expected HTTP status 406 for username too long request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("User attributes cannot be longer than 30 characters"),
                "Expected error message to mention 'User attributes cannot be longer than 30 characters'");
    }

    @Test
    void invalidPassword() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.INVALID_PASSWORD_REQUEST)
        ).andReturn();

        Assertions.assertEquals(406, result.getResponse().getStatus(),
                "Expected HTTP status 406 for invalid password request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Password is not valid"),
                "Expected error message to mention 'Password is not valid'");
    }

    @Test
    void invalidEmail() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.INVALID_EMAIL_REQUEST)
        ).andReturn();

        Assertions.assertEquals(406, result.getResponse().getStatus(),
                "Expected HTTP status 406 for invalid email request");

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseBody.contains("Email is not valid"),
                "Expected error message to mention 'Email is not valid'");
    }
}
