package bike.service.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

    public static final int AtomicReference = 0;

    private final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private OrderRepository oRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BikeRepository bikeRepository;

    public List<Users> getAllUsers() {
    logger.info("getAllUsers");
        List<Users> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            return new ArrayList<>();
        }
    }

    public Users getUserById(long id) {
        logger.info("get user by Id");
        Optional<Users> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User ID not found.");
        }
    }

    public Users addNewUser(Users user) {
        logger.info("add new user");

        if (user.getUserId() == 0) {
            userRepository.save(user);
        } else {
            throw new RuntimeException("User already exsist");
        }
        return user;
    }

    public void deleteUserById(long id) {
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

    public void deleteOrderById(int id) {

        Order order = oRepository.getReferenceById(id);
        List<Client> clients = clientRepository.findAll();
        List<Bike> bikes = bikeRepository.findAll();
        // List<Services> services = servicesRepository.findAll();

        if (order.getStatus().equals(Order.Status.DONE)) {
            for (Client client : clients) {
                client.getOrder();
                clientRepository.deleteById(client.getClientId());
            }

            for (Bike bike : bikes) {
                bike.getOrder();
                bikeRepository.deleteById(bike.getBikeId());
            }

            if (!(order.getOrderId() == 0)) {
                oRepository.deleteById(id);
                System.out.println("order deleted");
            }
        }
    }

    private LocalDate nowDate = LocalDate.now();

    public void newStatusById(int id, long userId) {
        Optional<Order> optional = oRepository.findById(id);
        Optional<Users> oUser = userRepository.findById((userId));
        if (optional.isPresent() && oUser.isPresent()) {
            optional.get().setStatus(Status.ACTIVE);
            optional.get().setUser(oUser.get());
            orderService.saveUserToOrder(optional.get(), userId);           
            oRepository.save(optional.get());
        }
    }

    public void doneStatusById(int id, long userId) {
        Optional<Order> optional = oRepository.findById(id);
        Optional<Users> oUser = userRepository.findById((userId));
        if (optional.isPresent() && oUser.isPresent()) {
            Order newOrder = optional.get();
            newOrder.setDate(nowDate.toString());
            newOrder.setUser(oUser.get());
            newOrder.setStatus(Order.Status.DONE);

            oRepository.save(newOrder);
        }
    }

    public void editOrderById(String edit, int id) {
        Optional<Order> orderOptional = oRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order newOrder = orderOptional.get();
            newOrder.setService(edit);

            oRepository.save(newOrder);
        }
    }

    public Order getOrderById(int id) {
        Optional<Order> order = oRepository.findById(id);
        return order.get();
    }
}