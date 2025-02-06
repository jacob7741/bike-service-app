package bike.service.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

    public static final int AtomicReference = 0;

    private final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository userRepository;
    @SuppressWarnings("unused")
    @Autowired
    private OrderService orderService;

    public List<Users> getAllUsers() {
        logger.info("getAllUsers");
        List<Users> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            return new ArrayList<>();
        }
    }

    public Users getUserById(int id) {
        logger.info("get user by Id");
        Optional<Users> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User ID not found.");
        }
    }

    // zakomentowana 5 lutego
    // public List<Users> findByIds(List<Integer> userIds) {
    // return userRepository.findAllById(userIds);
    // }

    // ToDO: zmienic nazwe metody na add new user
    public Users addNewMechanic(Users user) {
        logger.info("add new user");

        if (user.getUserId() == 0) {
            userRepository.save(user);
        } else {
            throw new RuntimeException("User already exsist");
        }
        return user;
    }

    public void deleteUserById(int id) {
        logger.info("deleted user by id");
        Optional<Users> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("no user was found");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("load users list");
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().toString().toUpperCase())
                .build();
    }
}