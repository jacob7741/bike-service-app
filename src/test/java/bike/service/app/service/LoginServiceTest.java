package bike.service.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService lService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UsersRepository uRepository;

    @Mock
    private UsersService uService;

    @Mock
    private OrderService oService;

    @BeforeEach
    public void setUp() {
        
    }
    @Test
    void testGetPersonalList() {

    }
    @Test
    void testUpdatePasswords() {

    }
}
