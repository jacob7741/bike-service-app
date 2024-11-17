package bike.service.app.service;

import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.ServicesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private ServicesRepository servicesRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ServicesService servicesService;

    @InjectMocks
    private OrderService orderService;


    @Test
    void saveServiceToOrder() {
//        Arrange
        Services services = new Services();
        services.setSmallService(50);
        services.setServiceId(12);


//        Act
        when(servicesRepository.save(any(Services.class))).thenReturn(services);

        Services savedService = servicesService.createNewService("servicetype", services);
        Order savedOrder = orderService.saveServiceToOrder(savedService);

//        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
//        Assert

        assertNotNull(savedOrder);
        assertEquals("small service - id: 50" , savedOrder.getService());
    }

    @Test
    void saveClientToOrder() {
    }

    @Test
    void saveBikeToOrder() {
    }
}