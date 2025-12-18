package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private OrderRepository oRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void getOrderById_whenOrderExists_returnsOrder() {
        Order order = mock(Order.class);
        when(oRepository.findById(1)).thenReturn(Optional.of(order));

        Order result = usersService.getOrderById(1);

        assertSame(order, result);
        verify(oRepository).findById(1);
    }

    @Test
    void getOrderById_whenOrderNotFound_throwsNoSuchElementException() {
        when(oRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> usersService.getOrderById(2));
        verify(oRepository).findById(2);
    }

    @Test
    void shouldUpdatingDoneStatus() {
        // given
        Users testUser = new Users();
        testUser.setUserId(34);
        
        Order order = new Order();
        order.setOrderId(123);
        order.setStatus(Order.Status.ACTIVE);
        
        String currentDate = LocalDate.now().toString();
        
        when(oRepository.findById(123)).thenReturn(Optional.of(order));
        when(usersRepository.findById(testUser.getUserId())).thenReturn(Optional.of(testUser));

        // when
        usersService.doneStatusById(123, 34);

        // then
        assertEquals(Order.Status.DONE, order.getStatus());
        verify(oRepository).save(order);
        assertEquals(currentDate, order.getDate());
    }

    @Test
    void newStatusById() {

        Users testUsers = new Users();
        testUsers.setUserId(23);

        Order testOrder = new Order();
        testOrder.setOrderId(54);
        testOrder.setStatus(Order.Status.NEW);

        when(oRepository.findById(54)).thenReturn(Optional.of(testOrder));
        when(usersRepository.findById(testUsers.getUserId())).thenReturn(Optional.of(testUsers));

        usersService.newStatusById(54, 23);

        assertEquals(Order.Status.ACTIVE, testOrder.getStatus());
    }
}