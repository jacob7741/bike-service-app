// package bike.service.app.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import bike.service.app.model.Services;
// import bike.service.app.model.repository.ServicesRepository;

// @ExtendWith(MockitoExtension.class)
// class ServicesServiceTest {
//     @Mock
//     private ServicesRepository servicesRepository;

//     @InjectMocks
//     private ServicesService servicesService;

   
    
//     @Test
//     void deletedServiceByIdException() {
//         Services nServices = new Services();
//         nServices.setServiceId(12);

//         int nonExsistId = 98;

//         Exception exception = assertThrows(RuntimeException.class, () -> {
//             servicesService.deletedServicesById(nonExsistId);
//         });

//         assertEquals("serviceNotDeleted", exception.getMessage());
//     }


//     void getServiceIdException() {
//         Services nServices = new Services();
//         nServices.setServiceId(12);

//         int nonExsistId = 98;

//         Exception exception = assertThrows(RuntimeException.class, () -> {
//             servicesService.getServicesById(nonExsistId);
//         });

//         assertEquals("servicesNotFound", exception.getMessage());
//     }

//     @Test
//     void getAllServicesIfServicesNoExist() {
//         List<Services> servicesList = new ArrayList<>();

//         when(servicesRepository.findAll()).thenReturn(servicesList);

//         List<Services> result = servicesService.getAllServices();

//         assertEquals(servicesList, result);
//         assertEquals(0, result.size());
//     }

//     @Test
//     void getAllServicesIfServicesExist() {
//         // Arrange
//         List<Services> servicesList = new ArrayList<>();
//         Services smallService = new Services();
//         smallService.setServiceId(1);
//         smallService.setSmallService(50);
//         smallService.setFullService(0);
//         smallService.setRepair(0);
//         smallService.setRepairType("N/A");

//         servicesList.add(smallService);

//         // servicesList.add(new Services(2, 33,
//         //         54, 12, "kkk", null));

//         when(servicesRepository.findAll()).thenReturn(servicesList);
//         // Act
//         List<Services> result = servicesService.getAllServices();
//         // Assert
//         assertEquals(servicesList, result);
//         assertEquals(2, result.size());
//     }

//     @Test
//     void getServicesById() {
//         // Arrange
//         Services services = new Services();
//         services.setServiceId(12);

//         when(servicesRepository.findById(12)).thenReturn(Optional.of(services));
//         // Act
//         Services result = servicesService.getServicesById(12);

//         // Assert
//         assertEquals(services, result);
//     }

//     @Test
//     void deletedServicesBy_Id() {
//         Services service = new Services();
//         service.setServiceId(41);

//         when(servicesRepository.findById(41)).thenReturn(Optional.of(service));

//         servicesService.deletedServicesById(41);

//         verify(servicesRepository, times(1)).deleteById(41);
//     }

//     @Test
//     void testCreateRepairService() {
//         Services service = new Services();

//         when(servicesRepository.save(any(Services.class))).thenReturn(service);

//         servicesService.createRepairService(service, "season", 23);

//         assertEquals(service, service);
//         assertEquals("season", service.getRepairType());
//     }
// }