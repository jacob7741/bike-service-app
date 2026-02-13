package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.DTO.ReadDTOservice;
import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UsersRepository userRepository;

    @Mock
    private UsersService userService;

    @InjectMocks
    private OrderService orderService;

    // wspólne obiekty używane w testach
    private Long commonUserId;
    private Users commonUser;
    private Bike commonBike;
    private Client commonClient;
    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        // wspólny user
        commonUserId = 42L;
        commonUser = new Users();
        commonUser.setUserId(commonUserId);
        commonUser.setFirstName("Jan");
        commonUser.setLastName("Kowalski");

        // wspólny bike
        commonBike = new Bike();
        commonBike.setModelType("ModelX");
        commonBike.setBrand("BrandY");

        // wspólny client
        commonClient = new Client();
        commonClient.setFirst_name("Adam");
        commonClient.setLast_name("Nowak");

        // wspólne zamówienia
        order1 = new Order();
        order1.setOrderId(1L);
        order1.setUserId(commonUserId);
        order1.setStatus(Status.ACTIVE);
        order1.setBike(commonBike);
        order1.setClient(commonClient);
        order1.setDate("2026-02-09");
        order1.setDeliveryDate("2026-02-15");

        order2 = new Order();
        order2.setOrderId(2L);
        order2.setUserId(commonUserId);
        order2.setStatus(Status.ACTIVE);
        order2.setBike(commonBike);
        order2.setClient(commonClient);
        order2.setDate("2026-02-10");
        order2.setDeliveryDate("2026-02-16");

        // domyślne zachowanie mocków — można nadpisać w konkretnych testach
        when(userRepository.findByUserId(commonUserId)).thenReturn(commonUser);
        when(orderRepository.findByUserIdAndStatus(commonUserId, Status.ACTIVE))
                .thenReturn(Collections.emptyList());
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());
    }

    @Test
    void saveInfoAddByUserId_whenUserIdIsZero_shouldNotCallSaveAndReturnUnchangedOrder() {
        Order order = new Order();
        order.setAddByUser(null);
        order.setDate(null);

        Order result = orderService.saveInfoAddByUserId(order, 0L);

        assertSame(order, result);
        verify(orderRepository, never()).save(any());
        assertNull(result.getAddByUser());
        assertNull(result.getDate());
    }

    @Test
    void saveInfoAddByUserId_whenMatchingUserExists_shouldSetAddByUserAndDateAndSave() {
        Order order = new Order();
        Users user = new Users();
        user.setUserId(5L);
        user.setLastName("Smith");

        // nadpisujemy domyślny stub userService tylko dla tego testu
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        String expectedDate = LocalDate.now().toString();
        Order result = orderService.saveInfoAddByUserId(order, 5L);

        verify(orderRepository, times(1)).save(order);
        assertEquals("Smith 5", result.getAddByUser());
        assertEquals(expectedDate, result.getDate());
        assertEquals(5L, user.getUserId());
    }

    @Test
    void saveInfoAddByUserId_whenNoMatchingUser_shouldNotCallSaveAndReturnOrderUnchanged() {
        Order order = new Order();
        Users user = new Users();
        user.setUserId(10L);
        user.setLastName("Jones");

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        Order result = orderService.saveInfoAddByUserId(order, 5L);

        verify(orderRepository, never()).save(any());
        assertNull(result.getAddByUser());
        assertNull(result.getDate());
    }

    @Test
    void getActiveOrderByUserId_returnsMappedReadDTOs_andCallsRepositories() {
        // używamy wspólnych obiektów, nadpisujemy stuby aby zwrócić konkretną listę
        when(orderRepository.findByUserIdAndStatus(commonUserId, Status.ACTIVE))
                .thenReturn(List.of(order1));
        when(userRepository.findByUserId(commonUserId)).thenReturn(commonUser);

        List<ReadDTOservice> result = orderService.getActiveOrdersByUserId(commonUserId);

        assertNotNull(result);
        assertEquals(1, result.size());

        ReadDTOservice dto = result.get(0);
        assertEquals(order1.getOrderId(), dto.getOrderId());
        assertEquals(commonUser.getUserId(), dto.getUserId());
        assertEquals(Status.ACTIVE, dto.getStatus());
        assertEquals("ModelX - BrandY", dto.getBike());
        assertEquals("Adam Nowak", dto.getClient());

        verify(orderRepository, times(1)).findByUserIdAndStatus(commonUserId, Status.ACTIVE);
        verify(userRepository, times(1)).findByUserId(commonUserId);
        verifyNoMoreInteractions(orderRepository, userRepository);
    }

    @Test
    void getAllActiveOrdersByUserId_returnsMappedReadDTOs_andCallsRepositories() {
        // nadpisujemy stuby aby zwrócić dwie pozycje
        when(orderRepository.findByUserIdAndStatus(commonUserId, Status.ACTIVE))
                .thenReturn(Arrays.asList(order1, order2));
        when(userRepository.findByUserId(commonUserId)).thenReturn(commonUser);

        List<ReadDTOservice> result = orderService.getActiveOrdersByUserId(commonUserId);

        assertNotNull(result);
        assertEquals(2, result.size(), "Powinny być dwa DTO w wyniku");

        ReadDTOservice dto1 = result.get(0);
        ReadDTOservice dto2 = result.get(1);

        assertEquals(order1.getOrderId(), dto1.getOrderId());
        assertEquals(order2.getOrderId(), dto2.getOrderId());
        assertEquals(commonUser.getUserId(), dto1.getUserId());
        assertEquals(commonUser.getUserId(), dto2.getUserId());

        verify(orderRepository, times(1)).findByUserIdAndStatus(commonUserId, Status.ACTIVE);
        verify(userRepository, times(1)).findByUserId(commonUserId);
        verifyNoMoreInteractions(orderRepository, userRepository);
    }

    @Test
    void CreateDTOClient() {
        // test placeholder — użyj wspólnych obiektów order1/order2/commonClient jeśli potrzebujesz
    }
}
