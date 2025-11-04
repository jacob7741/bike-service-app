package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import bike.service.app.model.Order;
import bike.service.app.model.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private OrderRepository oRepository;

    @InjectMocks
    private UsersService usersService;

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
}