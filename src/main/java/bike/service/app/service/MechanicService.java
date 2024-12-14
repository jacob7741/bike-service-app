package bike.service.app.service;

import bike.service.app.model.Mechanic;
import bike.service.app.model.repository.MechanicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MechanicService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MechanicService.class);

    @Autowired
    private MechanicRepository mechanicRepository;
    @Autowired
    private OrderService orderService;

    public List<Mechanic> getAllMechanics() {
        logger.info("getAllMechanics");
        List<Mechanic> mechanics = mechanicRepository.findAll();
        if (!mechanics.isEmpty()) {
            return mechanics;
        } else {
            return new ArrayList<>();
        }
    }

    public Mechanic getMechanicById(int id) {
        logger.info("get mechanic by Id");
        Optional<Mechanic> mechanic = mechanicRepository.findById(id);

        if (mechanic.isPresent()) {
            return mechanic.get();
        } else {
            throw new RuntimeException("no mechanic was found");
        }
    }

    public List<Mechanic> findByIds(List<Integer> mechanicIds) {
        return mechanicRepository.findAllById(mechanicIds);
    }

    public Mechanic addNewMechanic(Mechanic mechanic) {
        logger.info("add new mechanic");

        if (mechanic.getMechanicId() == 0) {
            mechanicRepository.save(mechanic);
        } else {
            throw new RuntimeException("wrong mechanic");
        }
        return mechanic;
    }

    public void deleteMechanicById(int id) {
        logger.info("deleted mechanic by id");
        Optional<Mechanic> mechanic = mechanicRepository.findById(id);

        if (mechanic.isPresent()) {
            mechanicRepository.deleteById(id);
        } else {
            throw new RuntimeException("no mechanic was found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("load mechanics list");
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