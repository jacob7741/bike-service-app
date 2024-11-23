package bike.service.app.service;

import bike.service.app.model.Mechanic;
import bike.service.app.model.repository.MechanicRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MechanicServiceTest {

    @Mock
    private MechanicRepository mechanicRepository;

    @InjectMocks
    private MechanicService mechanicService;

    @Test
    void getAllMechanics() {
    }

    @Test
    void getMechanicById() {
        Mechanic mechanic = new Mechanic();
        mechanic.setMechanicId(35);

        when(mechanicRepository.findById(35)).thenReturn(Optional.of(mechanic));

        Mechanic result = mechanicService.getMechanicById(35);

        assertEquals(mechanic, result);
    }
}