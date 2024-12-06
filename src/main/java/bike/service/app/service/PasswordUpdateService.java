package bike.service.app.service;

import bike.service.app.model.Mechanic;
import bike.service.app.model.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordUpdateService {

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
