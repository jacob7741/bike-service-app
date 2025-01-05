package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Bike;
import bike.service.app.model.repository.BikeRepository;

@ExtendWith(MockitoExtension.class)
public class BikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;
    @InjectMocks
    private BikeService bikeService;
    @Mock
    private Bike bike;

    @BeforeEach
    void setUp() {
        bike = new Bike();
        bike.setBikeId(12);
    }

    @Test
    void testAddNewBike() {

        when(bikeRepository.save(any(Bike.class))).thenReturn(bike);

        Bike savedBike = bikeService.addNewBike(bike);

        assertEquals(12, bike.getBikeId());
        assertEquals(savedBike, bike);
        assertNotNull(bike);
    }
}
