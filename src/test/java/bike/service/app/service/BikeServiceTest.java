package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Bike;
import bike.service.app.model.Order;
import bike.service.app.model.repository.BikeRepository;

@ExtendWith(MockitoExtension.class)
public class BikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private BikeService bikeService;

    @Test
    void testAddNewBike() {

        Bike bike = new Bike(0, "Trek", "Gravel", 12345678, new Order());

        when(bikeRepository.save(any(Bike.class))).thenReturn(bike);

        Bike savedBike = bikeService.addNewBike(bike);

        assertEquals(0, bike.getBikeId());
        assertEquals(savedBike, bike);
        assertNotNull(bike);
    }

    @Test
    void testDeletedBikeById() {

        Bike bike = new Bike(123, "Trek", "Gravel", 12345678, new Order());

        when(bikeRepository.findById(123)).thenReturn(Optional.of(bike));
        doNothing().when(bikeRepository).deleteById(123);

        bikeService.deletedBikeById(123);

        verify(bikeRepository, times(1)).deleteById(123);
        assertNotEquals(bike.getBikeId(), 785);
    }

    @Test
    void testGetAllBike() {
        when(bikeRepository.findAll()).thenReturn(new ArrayList<>());

        List<Bike> bikes = bikeService.getAllBike();

        assertEquals(new ArrayList<>(), bikes, "The list shold be empty");
    }

    @Test
    void testGetAllBike_NonEmptyList() {
        List<Bike> bikeList = List.of(new Bike(), new Bike());
        when(bikeRepository.findAll()).thenReturn(bikeList);

        List<Bike> result = bikeService.getAllBike();

        assertEquals(bikeList, result, "The list should contain bikes");
    }

    @Test
    void testGetBikeById() {

        Bike bike = new Bike(123, "Trek", "Gravel", 12345678, new Order());

        when(bikeRepository.findById(bike.getBikeId())).thenReturn(Optional.of(bike));

        Optional<Bike> result = Optional.of(bikeService.getBikeById(bike.getBikeId()));

        assertTrue(result.isPresent(), "Bike should be found");
        assertEquals(bike.getBikeId(), result.get().getBikeId(), "The bike ID should match");
        assertEquals(bike.getBrand(), result.get().getBrand(), "The bike brand should match");

        assertEquals(123, bike.getBikeId());
    }
}
