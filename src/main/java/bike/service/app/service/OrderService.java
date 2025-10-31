
package bike.service.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @SuppressWarnings("unused")
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private UsersService userService;

    private LocalDate date = LocalDate.now();

    public List<Order> getAllOrders() {
        System.out.println("get all orders");
        List<Order> ordersList = orderRepository.findAll();
        if (ordersList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return ordersList;
        }
    }

    public Order createNewOrder(String serviceType, Order service, String comment, String deliveryDate, Double price) {

        if (service.getOrderId() == null) {
            service.setDate(date.toString());
            service.setComment(comment);
            service.setDeliveryDate(deliveryDate);
            service.setStatus(Status.NEW);
            service.setPrice(price);
            switch (serviceType) {
                case "smallService":
                case "fullService":
                case "otherService":
                    break;
            }
        }
        orderRepository.save(service);
        return service;
    }

    @SuppressWarnings("static-access")
    public List<Order> getAllActiveOrders() {

        return orderRepository.findAll().stream()
                .filter(order -> Order.Status.ACTIVE.equals(order.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Order> getAllDoneOrders() {

        return orderRepository.findAll().stream()
                .filter(order -> Order.Status.DONE.equals(order.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Order> getAllNewOrders() {

        return orderRepository.findAll().stream()
                .filter(order -> Order.Status.NEW.equals(order.getStatus()))
                .collect(Collectors.toList());
    }

    public Order saveInfoAddByUser(Order order, AtomicReference<String> fullName) {
        List<Users> test = userService.getAllUsers();
        LocalDate nowDate = LocalDate.now();
        if (fullName.get() != null) {
            for (Users users : test) {
                String userName = users.getFirstName() + " " + users.getLastName();
                if (userName.equals(fullName.get())) {
                    order.setAddByUser(users.getLastName());
                    order.setDate(nowDate.toString());
                    orderRepository.save(order);
                    break;
                }
            }
        }
        return order;
    }

    public Order saveInfoAddByUserId(Order order, Long userId) {
        List<Users> test = userService.getAllUsers();
        LocalDate nowDate = LocalDate.now();
        if (userId != 0) {
            for (Users users : test) {
                Long id = users.getUserId();
                if (id == userId) {
                    order.setAddByUser(users.getLastName());
                    order.setDate(nowDate.toString());
                    orderRepository.save(order);
                    break;
                }
            }
        }
        return order;
    }

    public Order saveUserToOrder(Order order, int userId) {
    Users user = userService.getUserById(userId);
    if (user == null) {
        throw new IllegalArgumentException("User with id " + userId + " not found");
    } else {
        order.setUser(user); // zakładam, że pole w Order to Users user;
    }

    return orderRepository.save(order);
}

    public Order saveClientToOrder(Order order, Client client) {
        System.out.println("saveClientToOrder");
        if (client.getClientId() == 0) {
            throw new RuntimeException("no client found");
        } else {
            order.setClient(client);
        }
        orderRepository.save(order);
        client.setOrder(order);
        clientRepository.save(client);
        return order;
    }

    public Order saveBikeToOrder(Order order, Bike bike) {
        System.out.println("saveBikeToOrder");
        if (!bike.getModelType().isEmpty()) {
            order.setBike(bike);
        } else {
            throw new RuntimeException("no bike found");
        }

        orderRepository.save(order);
        bike.setOrder(order);
        bikeRepository.save(bike);
        return order;
    }
}