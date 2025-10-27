package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BikeRepository bikeRepository;
    @InjectMocks
    private BikeService bikeService;
    @InjectMocks
    private OrderService orderService;
    @InjectMocks
    private ClientService clientService;
    @InjectMocks
    private UsersService usersService;

    private Order order;

    private Client client;

    private Bike bike;

    private Users user;

    // Arrange for all tests
    @BeforeEach
    void setup() {

        user = new Users();
        user.setUserId(43);

        order = new Order();
        order.setOrderId(12);
        order.setStatus(Order.Status.NEW);

        client = new Client();
        client.setClientId(12);
        client.setLast_name("Kowalski");

        bike = new Bike();
        bike.setModelType("Góral");
    }

    @Test
    void createNewOrder() {

        order.setOrderId(0);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.createNewOrder("smallService", order, "comment", "12.09.2026", null);

        assertNotNull(result);
        assertEquals("comment", result.getComment());
        assertEquals("12.09.2026", result.getDeliveryDate());
        assertEquals("NEW", result.getStatus().toString());
        assertEquals(null, result.getPrice());
    }

    @Test
    void getOrderByUserId() {
        
        order.setUser(user);
    }
}