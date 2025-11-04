package bike.service.app.service;

import java.time.LocalDate;
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
    private UsersService usersService;

    @Autowired
    private ClientRepository clientRepository;
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

    public Users getUserById(int id) {
        logger.info("get user by Id");
        Optional<Users> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User ID not found.");
        }
    }

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

    public void newStatusById(int id, AtomicReference<String> name) {
        Optional<Order> optional = oRepository.findById(id);
        if (optional.isPresent()) {
            Order newOrder = optional.get();
            if (newOrder.getStatus().equals(Status.NEW)) {
                newOrder.setStatus(Order.Status.ACTIVE);
                newOrder.setDate(nowDate.toString());
                // orderService.saveMechanicToOrder(newOrder, name);
            }
            oRepository.save(newOrder);
        }
    }

    public void doneStatusById(int id, AtomicReference<String> fullName) {
        Optional<Order> optional = oRepository.findById(id);
        if (optional.isPresent()) {
            Order newOrder = optional.get();
            newOrder.setStatus(Order.Status.DONE);
            newOrder.setDate(nowDate.toString());
            List<Users> lmechanics = usersService.getAllUsers();

            for (Users user : lmechanics) {
                if ((user.getFirstName() + " " + user.getLastName()).equals(fullName.get())) {
                    newOrder.setDoneByUser(user.getLastName());
                    ;
                }
                oRepository.save(newOrder);
            }
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