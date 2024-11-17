package bike.service.app.service;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {
    @Mock
    private ServicesRepository servicesRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ServicesService servicesService;

    @Test
    void createNewService() {
        // Tworzenie nowego obiektu Services z ustawionymi wartościami
        Services services = new Services();
        services.setSmallService(50);
        services.setFullService(200);

        // Symulacja zachowania servicesRepository
        when(servicesRepository.save(any(Services.class))).thenReturn(services);

        // Wywołanie metody i sprawdzenie wyników
        Services savedService = servicesService.createNewService("serviceType", services);

        // Assercje
        assertNotNull(savedService);
        assertEquals(50, savedService.getSmallService());
        assertEquals(200, savedService.getFullService());

        // Weryfikacja, że metoda save została wywołana
        verify(servicesRepository, times(1)).save(any(Services.class));
    }


    @Test
    void getAllServicesIfServicesNoExist() {
        List<Services> servicesList = new ArrayList<>();

        when(servicesRepository.findAll()).thenReturn(servicesList);

        List<Services> result = servicesService.getAllServices();

        assertEquals(servicesList, result);
        assertEquals(0, result.size());
    }

//    @Test
//    void getAllServicesIfServicesExist() {
//        //Arrange
//        List<Services> servicesList = new ArrayList<>();
//        servicesList.add(new Services(1, 50, 100, 200));
//        servicesList.add(new Services(2, 60, 110, 210));
//        when(servicesRepository.findAll()).thenReturn(servicesList);
//        //Act
//        List<Services> result = servicesService.getAllServices();
//        // Assert
//        assertEquals(servicesList, result);
//        assertEquals(2, result.size());
//    }

    @Test
    void getServicesById() {
        //Arrange
        Services services = new Services();
        services.setServiceId(12);

        when(servicesRepository.findById(12)).thenReturn(Optional.of(services));
        //Act
        Services result = servicesService.getServicesById(12);

        //Assert
        assertEquals(services, result);
    }

    @Test
    void deletedServicesBy_Id() {
        Services service = new Services();
        service.setServiceId(41);

        when(servicesRepository.findById(service.getServiceId())).thenReturn(Optional.of(service));

        servicesService.deletedServicesById(service.getServiceId());

        verify(servicesRepository, times(1)).deleteById(service.getServiceId());
    }
}