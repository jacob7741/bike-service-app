package bike.service.app.service;

import bike.service.app.model.Mechanic;
import bike.service.app.model.repository.MechanicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MechanicService {

    private final Logger logger = LoggerFactory.getLogger(MechanicService.class);

    @Autowired
    private MechanicRepository mechanicRepository;

    public List<Mechanic> getAllMechanics(Mechanic mechanic) {
        logger.info("getAllMechanicsToOrder");
        List<Mechanic> mechanics = mechanicRepository.findAll();
        if (!mechanics.isEmpty()) {
           return mechanics;
        } else {
            return new ArrayList<Mechanic>();
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
}
