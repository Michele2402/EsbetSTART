package unisa.esbetstart.game.getall;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllGamesTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void shouldFindAllGames() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/games/get-all"))
                .andReturn();

        //l'unica cosa che si può controllare è che la chiamata funzioni
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

}
