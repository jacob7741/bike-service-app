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

import bike.service.app.DTO.ReadDTOservice;
import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
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
        // Bike bike = new Bike(12, "Trek", "Mountian", 123423, order);
        // Client client = new Client(34, "Zenon", "Nowak", "234-531-798","nowek@po.pl", order);

        // order.setOrderId(3452);
        order.setStatus(Status.ACTIVE);
        // order.setClient(client);
        // order.setBike(bike);
        order.setDate("12-02-2024");
        order.setDeliveryDate("18-02-2024");
        user.setUserId(48L);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");

        when(oRepository.findOrderByUserId(48L)).thenReturn(List.of(order));
        when(uRepository.findByUserId(48L)).thenReturn(user);

        List<ReadDTOservice> listDTO = lService.getOrderByUserIdDTO(user.getUserId());

        ReadDTOservice dto = listDTO.get(0);

        assertEquals(3452, dto.getOrderId());
        assertEquals("Jan", dto.getFirstName());
        assertEquals("Zenon Nowak", dto.getClient());
        assertEquals("Mountian - Trek", dto.getBike());
        assertEquals("12-02-2024", dto.getDate());
        assertEquals("18-02-2024", dto.getDeliveryDate());
    }
}