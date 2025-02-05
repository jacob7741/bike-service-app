package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import bike.service.app.model.Users;
import bike.service.app.model.Users.Role;
import bike.service.app.model.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @InjectMocks
    private UsersService uService;

    @Mock
    private UsersRepository uRepository;

    @Mock
    private OrderService oService;

    private Users user;

    private List<Users> listUsers;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setFirstName("Alfonso");
        user.setLastName("Opieniek");
        user.setPassword("password");
        user.setRole(Role.MECHANIC);
        user.setUserId(2929);
        user.setUserName("Dude");

        listUsers = Arrays.asList(user);
    }

    @Test
    void testAddNewMechanic() {

        user.setUserId(0);

        when(uRepository.save(any(Users.class))).thenReturn(user);

        Users result = uService.addNewMechanic(user);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void testAddNewMechanicException() {
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            uService.addNewMechanic(user);
        });

        assertEquals("User already exsist", exception.getMessage());
    }

    @Test
    void testDeleteUserById() {

        when(uRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        Users result = uService.getUserById(user.getUserId());
        uService.deleteUserById(2929);

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
    }

    @Test
    void testFindByIds() {

        when(uRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        Users result = uService.getUserById(2929);

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
    }

    @Test
    void testGetAllUsers() {
        List<Users> usersList = listUsers;

        when(uRepository.findAll()).thenReturn(usersList);

        List<Users> result = uService.getAllUsers();

        assertNotNull(result);
        assertEquals(usersList.size(), result.size());
    }

    @Test
    void testGetUserById() {

        when(uRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        Users result = uService.getUserById(user.getUserId());

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
    }

    @Test
    void testGetUserByIdException() {

        int nonExsist = 999;

        when(uRepository.findById(nonExsist)).thenReturn(Optional.empty());

        int test = user.getUserId();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            uService.getUserById(nonExsist);
        });

        assertEquals("User ID not found.", exception.getMessage());
    }

    @Test
    void testLoadUserByUsername() {

        when(uRepository.findByUserName(user.getUserName())).thenReturn(user);

        UserDetails uDetails = uService.loadUserByUsername(user.getUserName());

        assertNotNull(uDetails);
        assertEquals(user.getUserName(), uDetails.getUsername());
        assertEquals(user.getPassword(), uDetails.getPassword());
        assertEquals("ROLE_" + user.getRole(), uDetails.getAuthorities()
                .iterator().next().getAuthority());
    }
}
