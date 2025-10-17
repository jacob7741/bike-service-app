package bike.service.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import bike.service.app.controller.LoginController;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	private LoginController loginController;

	@Test
	void contextLoads() throws Exception {
		assertThat(loginController).isNotNull();
	}

}
