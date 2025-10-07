package bike.service.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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

        Order result = orderService.createNewOrder("smallService", order, "comment", "12.09.2026");

        assertNotNull(result);
        assertEquals("comment", result.getComment());
        assertEquals("12.09.2026", result.getDeliveryDate());
        assertEquals("NEW", result.getStatus().toString());
    }

    @Test
    void saveMechanicToOrder() {
        Users testUser = new Users();
        testUser.setUserId(34);

        Order order = new Order();
        order.setOrderId(12);
        order.setMechanic(testUser);

        assertNotNull(order);
        assertEquals(testUser.getUserId(), order.getMechanic().getUserId());
    }

    @Test
    void saveClientToOrderException() {

        client.setClientId(0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.saveClientToOrder(order, client);
        });

        assertEquals("no client found", exception.getMessage());
    }

    @Test
    void saveClientToOrder() {

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order clientSaved = orderService.saveClientToOrder(order, client);

        assertNotNull(clientSaved);
        assertEquals("Kowalski id: 12", clientSaved.getClient());
    }

    @Test
    void saveBikeToOrder() {

        when(bikeRepository.save(any(Bike.class))).thenReturn(bike);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order bikeSaved = orderService.saveBikeToOrder(order, bike);

        assertNotNull(bikeSaved);
        assertEquals("Góral", bikeSaved.getBikeModel());
    }

    @Test
    void saveBikeToOrderException() {

        bike.setModelType("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.saveBikeToOrder(order, bike);
        });

        assertEquals("no bike found", exception.getMessage());
    }

    @Test
    void testGetAllNewOrders() {
        Order order1 = new Order();
        order1.setStatus(Order.Status.NEW);
        Order order2 = new Order();
        order2.setStatus(Order.Status.NEW);

        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> newOrders = orderService.getAllNewOrders();

        assertThat(newOrders).containsExactly(order1, order2);
    }

    @Test
    void testGetAllDoneOrders() {
        Order order1 = new Order();
        order1.setStatus(Order.Status.NEW);

        List<Order> orders = Arrays.asList(order1);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> doneOrders = orderService.getAllDoneOrders();

        assertThat(doneOrders).containsExactly(order1);
    }
}