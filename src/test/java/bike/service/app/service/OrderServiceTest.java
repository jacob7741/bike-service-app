package bike.service.app.service;

import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    private ServicesRepository servicesRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private ServicesService servicesService;
    @InjectMocks
    private OrderService orderService;
    @InjectMocks
    private ClientService clientService;

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
        client.setClientId(87230);
        client.setLast_name("Kowalski");

        Client clientSaved = clientService.addNewClient(client);

        assertNotNull(clientSaved);
        assertEquals(87230, clientSaved.getClientId());
        assertEquals("Kowalski", clientSaved.getLast_name());
    }

    @Test
    void saveBikeToOrder() {
    }
}