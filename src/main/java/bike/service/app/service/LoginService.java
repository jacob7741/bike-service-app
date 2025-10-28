package bike.service.app.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;

@Service
public class LoginService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users setFullName(String mechanicName, AtomicReference<String> fullName) {
        List<Users> userList = usersService.getAllUsers();
        // mechanicName = getContextHolder();
        for (Users user : userList) {
            if (user.getUserName().equals(mechanicName)) {
                fullName.set(user.getFirstName() + " " + user.getLastName());
                return user;
            }
        }
        throw new RuntimeException("Wrong mechanic name." + mechanicName);
    }

    public void updatePasswords() {
        List<Users> usersList = usersService.getAllUsers();

        for (Users user : usersList) {
            String rawPassword = user.getPassword();
            if (!rawPassword.startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                user.setPassword(encodedPassword);
                usersRepository.save(user);
            }
        }
    }

    public List<Order> getOrderByUserId(Long userId) {
        return orderRepository.findByUserUserId(userId);
    }
    
}