package bike.service.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bike.service.app.model.Mechanic;
import bike.service.app.model.repository.MechanicRepository;

@Service
public class LoginService {
    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void updatePasswords() {
        List<Mechanic> mechanics = mechanicRepository.findAll();
        for (Mechanic mechanic : mechanics) {
            String rawPassword = mechanic.getPassword();
            if (!rawPassword.startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                mechanic.setPassword(encodedPassword);
                mechanicRepository.save(mechanic);
            }
        }
    }
}