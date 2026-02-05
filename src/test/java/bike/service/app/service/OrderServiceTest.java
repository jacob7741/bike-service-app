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
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.DTO.ReadDTOservice;
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
    void getActiveOrderByUserId() {
        Users user = new Users();
        user.setUserId(342);
        
        Order order = new Order();
        order.setStatus(Status.ACTIVE);
        
        when(userRepository.findById(342L)).thenReturn(Optional.of(user));
        when(orderRepository.findOrderByUserId(342L)).thenReturn(List.of(order));

       List<Order> activeOrder = orderRepository.findByUserIdAndStatus(342L, Status.ACTIVE);

        assertNotNull(activeOrder);
        assertEquals(Status.ACTIVE, activeOrder.get(0).getStatus());
    }

    @Test
    void getAllActiveOrdersByUserId_returnsMappedReadDTOs_andCallsRepositories() {
        // given
        Long userId = 42L;
        Users user = new Users();
        user.setUserId(userId);
        user.setFirstName("Jan");

        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setUserId(userId);
        order1.setStatus(Status.ACTIVE);

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setUserId(userId);
        order2.setStatus(Status.ACTIVE);

        when(orderRepository.findByUserIdAndStatus(userId, Status.ACTIVE))
            .thenReturn(Arrays.asList(order1, order2));
        when(userRepository.findByUserId(userId)).thenReturn(user);

        // when
        List<ReadDTOservice> result = orderService.getAllActiveOrdersByUserId(userId);

        // then
        assertNotNull(result);
        assertEquals(2, result.size(), "Powinny być dwa DTO w wyniku");

        ReadDTOservice dto1 = result.get(0);
        ReadDTOservice dto2 = result.get(1);

        assertEquals(order1.getOrderId(), dto1.getOrderId());
        assertEquals(order2.getOrderId(), dto2.getOrderId());
        assertEquals(user.getUserId(), dto1.getUserId());
        assertEquals(user.getUserId(), dto2.getUserId());

        verify(orderRepository, times(1)).findByUserIdAndStatus(userId, Status.ACTIVE);
        verify(userRepository, times(1)).findByUserId(userId);
        verifyNoMoreInteractions(orderRepository, userRepository);
    }
}