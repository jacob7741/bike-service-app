package bike.service.app.config;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ServicesConfigTest {

    @InjectMocks
    private ServicesConfig servicesConfig;

    @Mock
    private ServicesRepository servicesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewService_NewService() {
        Services service = new Services();
        service.setServiceId(0);

        when(servicesRepository.save(any(Services.class))).thenReturn(service);

        Services result = servicesConfig.createNewService(service);

        assertEquals(service, result);
    }

    @Test
    void testCreateNewService_ExistingService() {
        Services service = new Services();
        service.setServiceId(1);
        Optional<Services> optionalService = Optional.of(service);

        when(servicesRepository.findById(1L)).thenReturn(optionalService);
        when(servicesRepository.save(any(Services.class))).thenReturn(service);

        Services result = servicesConfig.createNewService(service);

        assertEquals(service, result);
    }

    @Test
    void testCreateNewService_ServiceNotFound() {
        Services service = new Services();
        service.setServiceId(1);

        when(servicesRepository.findById(1L)).thenReturn(Optional.empty());
        when(servicesRepository.save(any(Services.class))).thenReturn(service);

        Services result = servicesConfig.createNewService(service);

        assertEquals(service, result);
    }
}
