package bike.service.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.repository.UsersRepository;

@Service
public class LoginService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderService orderService;

    private String getContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Users setFullName(String mechanicName, AtomicReference<String> fullName) {
        List<Users> userList = usersService.getAllUsers();
        mechanicName = getContextHolder();
        for (Users user : userList) {
            if (user.getUserName().equals(mechanicName)) {
                fullName.set(user.getFirstName() + " " + user.getLastName());
                return user;
            }
        }
        throw new RuntimeException("Wrong mechanic name.");
    }

    public List<Order> getMechanicList(Users user) {
        List<Order> ordersList = orderService.getAllActiveOrders();
        List<Order> pList = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getMechanic().getLastName().equals(user.getLastName())) {
                pList.add(order);
            }
        }
        return pList;
    }

    public List<Order> getPersonalList(AtomicReference<String> fullName) {
        String mechanicName = getContextHolder();
        Users user = setFullName(mechanicName, fullName);
        return getMechanicList(user);
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
    
        // old from 08.02.2025 before refactoring
    
        // public List<Order> getPersonalList(AtomicReference<String> fullName) {
    
        //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //     String mechanicName = authentication.getName();
        //     List<Users> usersList = usersService.getAllUsers();
        //     List<Order> ordersList = orderService.getAllActiveOrders();
    
        //     List<Order> orderList = new ArrayList<>();
        //     for (Users user : usersList) {
        //         if (user.getUserName().equals(mechanicName)) {
        //             fullName.set(user.getFirstName() + " " + user.getLastName());
        //             for (Order order : ordersList) {
        //                 if (order.getMechanic().getLastName().equals(user.getLastName())) {
        //                     orderList.add(order);
        //                 }
        //             }
        //         }
        //     }
        //     return orderList;
        // }
}