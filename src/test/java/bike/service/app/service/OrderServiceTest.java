package bike.service.app.service;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    private ServicesRepository servicesRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BikeRepository bikeRepository;
    @InjectMocks
    private BikeService bikeService;
    @InjectMocks
    private ServicesService servicesService;
    @InjectMocks
    private OrderService orderService;
    @InjectMocks
    private ClientService clientService;

//    @Test
//    void createOrderTest() {
//        Bike bike = new Bike();
//        bike.setModelType("Góral");
//
//        Client client = new Client();
//        client.setLast_name("Kowalski");
//
//
//        Services services = new Services();
//        services.setSmallService(50);
//        services.setServiceId(12);
//
//        Order order = new Order();
//        order.setMechanic("Mechanik Bob");
//        order.setOrderId(1853);
//        order.setServices(services);
//        order.setClient(client.getLast_name());
//        order.setBikeModel(bike.getModelType());
//
//       when(orderRepository.save(any(Order.class))).thenReturn(order);
//
//    }

    @Test
    void saveServiceToOrder() {
//        Arrange
        Services services = new Services();
        services.setSmallService(50);
        services.setServiceId(12);

        Order order = new Order();

        when(servicesRepository.save(any(Services.class))).thenReturn(services);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

//        Act
        Order savedOrder = orderService.saveServiceToOrder(services);

//        Assert
        assertNotNull(savedOrder);
        assertEquals("small service - id: 50", savedOrder.getService());
        assertEquals(12, savedOrder.getOrderId());
    }

    @Test
    void saveClientToOrder() {
        Client client = new Client();
        client.setLast_name("Kowalski");

        Order order = new Order();

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order clientSaved = orderService.saveClientToOrder(client);

        assertNotNull(clientSaved);
        assertEquals("Kowalski", clientSaved.getClient());
    }

    @Test
    void saveBikeToOrder() {
        Bike bike = new Bike();
        bike.setModelType("Góral");

        Order order = new Order();

        when(bikeRepository.save(any(Bike.class))).thenReturn(bike);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order bikeSaved = orderService.saveBikeToOrder(bike);

        assertNotNull(bikeSaved);
        assertEquals("model of bike is: Góral", bikeSaved.getBikeModel());
    }
}