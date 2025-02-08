package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import bike.service.app.model.Users.Role;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService lService;

    @Mock
    private UsersRepository uRepository;

    @Mock
    private UsersService uService;

    @Mock
    private OrderService oService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    private Users user;
    private List<Order> lOrders;
    private Order order;

    @BeforeEach
    public void setUp() {
        user = new Users();
        user.setFirstName("Alfonso");
        user.setLastName("Opieniek");
        user.setPassword("password");
        user.setRole(Role.MECHANIC);
        user.setUserId(2929);
        user.setUserName("Dude");

        order = new Order();
        order.setBikeModel("Trek");
        order.setClient("Kowalski");
        order.setMechanic(user);
        order.setStatus(Status.ACTIVE);

        lOrders = new ArrayList<>();
        lOrders.add(order);

        when(oService.getAllActiveOrders()).thenReturn(lOrders);
        when(uService.getAllUsers()).thenReturn(Arrays.asList(user));
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(user.getUserName());
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetPersonalList() {
        AtomicReference<String> uAtomicReference = new AtomicReference<>(user.getLastName());

        List<Order> personalOrders = lService.getPersonalList(uAtomicReference);
        String mechanicString = personalOrders.get(0).getMechanic().getLastName();

        assertNotNull(personalOrders);
        assertEquals(user.getFirstName() + " " + user.getLastName(), uAtomicReference.get());
        assertEquals(user.getLastName(), mechanicString);
    }

    @Test
    void testUpdatePasswords() {
        
    }
}