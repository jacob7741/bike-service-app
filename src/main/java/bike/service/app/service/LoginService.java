package bike.service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bike.service.app.model.Users;
import bike.service.app.model.repository.UsersRepository;

@Service
public class LoginService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void updatePasswords() {
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            String rawPassword = user.getPassword();
            if (!rawPassword.startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                user.setPassword(encodedPassword);
                usersRepository.save(user);
            }
        }
    }
}