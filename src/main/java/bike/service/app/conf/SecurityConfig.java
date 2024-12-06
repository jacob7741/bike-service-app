package bike.service.app.conf;

import bike.service.app.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MechanicService mechanicService;
    @Autowired
    private UserDetailsService userDetailsService;
    //    @Autowired
//    private MechanicDetailService mechanicDetailService;
    @Autowired
    private DataSource dataSource;

    @Bean
    public static JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT user_name AS username, password, true AS enabled FROM Mechanic WHERE user_name = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT user_name AS username, 'ROLE_MECHANIC' AS authority FROM Mechanic WHERE user_name = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**", "static/css/style.css").permitAll()
                        .anyRequest().authenticated()
                )
//                .userDetailsService(mechanicDetailService)
                .formLogin((form) -> form
                        .loginPage("/mainSite")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/mechanicSite", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
    //
//    @Autowired
//    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScripts("src/main/resources/schema.sql")
//                .build();
//    }
//      wersja testowa
//    @Bean
//    public UserDetailsService mechanicDetail() {
//
//
//        List<Mechanic> mechanicList = mechanicService.getAllMechanics();
//
//        Mechanic mechanic = mechanicList.get(1);
//
//        UserDetails userDetails = User.builder()
//                .username(mechanic.getUser_name())
//                .password("{noop}" + mechanic.getPassword())
//                .roles("MECHANIC")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }
}