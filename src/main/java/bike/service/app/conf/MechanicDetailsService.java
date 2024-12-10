package bike.service.app.conf;

import bike.service.app.model.Mechanic;
import bike.service.app.model.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MechanicDetailsService implements UserDetailsService {

    @Autowired
    private MechanicRepository mechanicRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mechanic mechanic = mechanicRepository.findByUserName(username);
        if (mechanic == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(mechanic.getUserName())
                .password(mechanic.getPassword())
                .build();
    }
}
