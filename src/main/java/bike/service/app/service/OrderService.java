
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

        if (service.getOrderId() == 0) {
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
        // List<Order> ordersList = orderRepository.findAll();
        // List<Order> activeOrders = new ArrayList<>();
        // for (Order order : ordersList) {
        // if(order.getStatus() != null &&
        // order.getStatus().equals(Order.Status.ACTIVE)) {
        // activeOrders.add(order);
        // }
        // }
        // return activeOrders;

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

    public List<Order> getOrderByUserId(Integer userId) {
        Users user = userRepository.findByUserId(userId);
        List<Order> userList = new ArrayList<>();

       
            if (user.getRole() == Users.Role.MECHANIC ) {
                
            } else if (user.getRole() == Users.Role.MANAGER) {
                
            }
        
        return userList;
    }

    // public Order saveMechanicToOrder(Order order, int id) {
    // System.out.println("saveMechanicToOrder");

    // Users mechanic = userService.getUserById(id);

    // order.setMechanic(mechanic);
    // System.out.println("Order before save: " + order);

    // orderRepository.save(order);
    // System.out.println("Order after save: " + order);
    // // mechanicRepository.save(mechanic);
    // return order;
    // }

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
    public Order saveInfoAddByUserId(Order order, Integer userId) {
        List<Users> test = userService.getAllUsers();
        LocalDate nowDate = LocalDate.now();
        if (userId != 0) {
            for (Users users : test) {
                Integer id = users.getUserId();
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

    // public Order saveMechanicToOrder(Order order, int id) {
    //     System.out.println("saveMechanicToOrder");

    //     Users mechanic = userService.getUserById(id);

    //     order.setMechanic(mechanic);
    //     System.out.println("Order before save: " + order);

    //     orderRepository.save(order);
    //     System.out.println("Order after save: " + order);
    //     // mechanicRepository.save(mechanic);
    //     return order;
    // }

    public Order saveUserToOrder(Order order, int id) {
        System.out.println("saveMechanicToOrder");

        Users user = userService.getUserById(id);

        order.setUserId(user);
        System.out.println("Order before save: " + order);

        orderRepository.save(order);
        System.out.println("Order after save: " + order);
        // mechanicRepository.save(mechanic);
        return order;
    }

    // public Order saveMechanicToOrder(Order order, AtomicReference<String> fullName) {
    //     System.out.println("saveMechanicToOrder");

    //     List<Users> lmechanics = userService.getAllUsers();

    //     for (Users user : lmechanics) {
    //         if ((user.getFirstName() + " " + user.getLastName()).equals(fullName.get())) {
    //             order.setUser(user);
    //         }
    //     }
    //     return order;
    // }

    // public Order saveServiceToOrder(Order order) {

    // System.out.println("saveServiceToOrder");

    // // if (services.getSmallService() == 50) {
    // // order.setService("small service - id: " + services.getServiceId());
    // // order.setData(services.getDate());
    // // } else if (services.getFullService() == 200) {
    // // order.setService("full service - id: " + services.getServiceId());
    // // order.setData(services.getDate());
    // // } else {
    // // order.setService("reprair - id: " + services.getServiceId());
    // // order.setData(services.getDate());
    // // }

    // order.setStatus(Status.NEW);
    // orderRepository.save(order);
    // // services.setOrder(order);
    // // servicesRepository.save(services);
    // return order;
    // }

    public Order saveClientToOrder(Order order, Client client) {
        System.out.println("saveClientToOrder");
        if (client.getClientId() == 0) {
            throw new RuntimeException("no client found");
        } else {
            // order.setClientId(client.getClientId());
        }
        orderRepository.save(order);
        client.setOrder(order);
        clientRepository.save(client);
        return order;
    }

    public Order saveBikeToOrder(Order order, Bike bike) {
        System.out.println("saveBikeToOrder");
        if (!bike.getModelType().isEmpty()) {
            // order.setBikeId(bike.getBikeId());
        } else {
            throw new RuntimeException("no bike found");
        }

        orderRepository.save(order);
        bike.setOrder(order);
        bikeRepository.save(bike);
        return order;
    }
}