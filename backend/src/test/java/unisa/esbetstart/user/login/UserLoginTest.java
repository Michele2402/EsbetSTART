package unisa.esbetstart.user.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import unisa.esbetstart.user.registration.UserRegistrationJsonPayloads;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserLoginTest {

    @Autowired
    MockMvc mockMvc;

    private void registerUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType("application/json")
                        .content(UserRegistrationJsonPayloads.VALID_REGISTER_REQUEST)
        ).andReturn();
    }

    @Test
    void validLoginRequest() throws Exception {

        registerUser();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType("application/json")
                        .content(UserLoginJsonPayloads.VALID_LOGIN_REQUEST)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus(),
                "Expected HTTP status 200 for valid login request");
    }

    @Test
    void invalidEmail() throws Exception {

        registerUser();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType("application/json")
                        .content(UserLoginJsonPayloads.INVALID_EMAIL_REQUEST)
        ).andReturn();

        Assertions.assertEquals(401, result.getResponse().getStatus(),
                "Expected HTTP status 401 for invalid email request");
    }

    @Test
    void invalidPassword() throws Exception {

        registerUser();

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType("application/json")
                        .content(UserLoginJsonPayloads.INVALID_PASSWORD_REQUEST)
        ).andReturn();

        Assertions.assertEquals(401, result.getResponse().getStatus(),
                "Expected HTTP status 401 for invalid password request");
    }
}
