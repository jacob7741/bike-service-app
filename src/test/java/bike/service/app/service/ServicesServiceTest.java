package bike.service.app.service;

import bike.service.app.model.Services;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ServicesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {
    @Mock
    private ServicesRepository servicesRepository;
    @Mock
    private BikeRepository bikeRepository;
    @InjectMocks
    private ServicesService servicesService;


    @Test
    void addBike() {
        // Tutaj dodać logikę testową dla metody addBike
    }

    @Test
    void createUpdateNewService() {
        // Tworzenie nowego obiektu Services z ustawionymi wartościami
        Services services = new Services();
        services.setSmallService(50);
        services.setFullService(200);

        // Symulacja zachowania servicesRepository
        when(servicesRepository.save(any(Services.class))).thenReturn(services);

        // Wywołanie metody i sprawdzenie wyników
        Services savedService = servicesService.createUpdateNewService("serviceType" ,services);

        // Assercje
        assertNotNull(savedService);
        assertEquals(50, savedService.getSmallService());
        assertEquals(200, savedService.getFullService());

        // Weryfikacja, że metoda save została wywołana
        verify(servicesRepository, times(1)).save(any(Services.class));
    }

}
