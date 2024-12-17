package bike.service.app.conf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bike.service.app.service.LoginService;

@Configuration
public class PasswordConfig {

    @Bean
    public CommandLineRunner commandLineRunner(LoginService passwordUpdateService) {
        return args -> {
            passwordUpdateService.updatePasswords();
        };
    }
}