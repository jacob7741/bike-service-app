package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.Order.Status;
import bike.service.app.model.repository.OrderRepository;



@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UsersService userService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void findByStatus() {
        Order oActive = new Order();
        Order oDone = new Order();
        Order oNew = new Order();

        oActive.setStatus(Status.ACTIVE);
        oDone.setStatus(Status.DONE);
        oNew.setStatus(Status.NEW);

        when(orderRepository.findByStatus(Status.NEW)).thenReturn(List.of(oNew));

        List<Order> sListN = orderService.getAllNewOrders();

        assertEquals(1, sListN.size());
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
}