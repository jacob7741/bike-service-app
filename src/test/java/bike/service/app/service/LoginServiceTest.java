package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import bike.service.app.DTO.OrderWithUserDTO;
import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.Order.Status;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService lService;

    @Mock
    private UsersRepository uRepository;

    @Mock
    private OrderRepository oRepository;

    @Mock
    private UsersService uService;

    @Mock
    private OrderService oService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testUpdatePasswords() {

        Users user1 = new Users();
        user1.setPassword("plainPassword1");
        Users user2 = new Users();
        user2.setPassword("$2a$encodedPassword2");

        List<Users> usersList = Arrays.asList(user1, user2);

        when(uService.getAllUsers()).thenReturn(usersList);
        when(passwordEncoder.encode("plainPassword1")).thenReturn("encodedPassword1");

        lService.updatePasswords();

        verify(uRepository).save(user1);
        verify(uRepository, never()).save(user2);

        assertEquals("encodedPassword1", user1.getPassword());
        assertEquals("$2a$encodedPassword2", user2.getPassword());
    }

    @Test
    void testDTOgetUsers() {

        Order order = new Order();
        Users user = new Users();

        order.setOrderId(3452);
        order.setStatus(Status.ACTIVE);
        user.setUserId(48L);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");

        when(oRepository.findOrderByUserId(48L)).thenReturn(List.of(order));
        when(uRepository.findByUserId(48L)).thenReturn(user);

        List<OrderWithUserDTO> listDTO = lService.getOrderByUserIdDTO(user.getUserId());

        OrderWithUserDTO dto = listDTO.get(0);

        assertEquals(3452, dto.getOrderId());
        assertEquals("Jan", dto.getFirstName());
    }
}