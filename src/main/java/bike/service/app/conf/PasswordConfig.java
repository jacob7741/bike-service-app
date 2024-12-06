package bike.service.app.conf;

import bike.service.app.service.PasswordUpdateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

    @Bean
    public CommandLineRunner commandLineRunner(PasswordUpdateService passwordUpdateService) {
        return args -> {
            passwordUpdateService.updatePasswords();
        };
    }
}
