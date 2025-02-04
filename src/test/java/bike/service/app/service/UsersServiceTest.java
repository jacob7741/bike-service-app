package bike.service.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    private static Users user() {
        Users user = new Users();
        user.setFirstName("Alfonso");
        user.setLastName("Opieniek");
        user.setPassword("password");
        user.setRole(Role.MECHANIC);
        user.setUserId(2929);
        user.setUserName("Dude");
        return user;
    }

    private List<Users> listUsers = Arrays.asList(user());

    @Test
    void testAddNewMechanic() {

        Users user = user();
        user.setUserId(0);

        when(uRepository.save(any(Users.class))).thenReturn(user);

        Users result = uService.addNewMechanic(user);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void testDeleteUserById() {
        Users users = user();

        when(uRepository.findById(users.getUserId())).thenReturn(Optional.of(users));

        Users result = uService.getUserById(users.getUserId());
        uService.deleteUserById(2929);

        assertNotNull(result);
        assertEquals(users.getUserId(), result.getUserId());
    }

    @Test
    void testFindByIds() {
        Users user  = user();

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
        Users users = user();

        when(uRepository.findById(users.getUserId())).thenReturn(Optional.of(users));

        Users result = uService.getUserById(users.getUserId());

        assertNotNull(result);
        assertEquals(users.getUserId(), result.getUserId());
    }

    @Test
    void testLoadUserByUsername() {
        
        Users user = user();

        when(uRepository.findByUserName(user.getUserName())).thenReturn(user);

        UserDetails uDetails = uService.loadUserByUsername(user.getUserName());

        assertNotNull(uDetails);
        assertEquals(user.getUserName(), uDetails.getUsername());
        assertEquals(user.getPassword(), uDetails.getPassword());
        assertEquals("ROLE_" + user.getRole(), uDetails.getAuthorities()
                                                        .iterator().next().getAuthority()); 
    }
}
