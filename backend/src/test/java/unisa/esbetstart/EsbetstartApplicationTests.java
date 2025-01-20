package unisa.esbetstart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unisa.esbetstart.usermanagment.presentation.controller.UserController;

@SpringBootTest
class EsbetstartApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {

	}

}
