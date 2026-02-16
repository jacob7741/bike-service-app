
package bike.service.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.DTO.ReadDTOservice;
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


    

    // public Order createNewOrder(String serviceType, Order service, String comment, String deliveryDate, Double price) {

    //     if (service.getOrderId() == 0) {
    //         service.setDate(date.toString());
    //         service.setComment(comment);
    //         service.setDeliveryDate(deliveryDate);
    //         service.setStatus(Status.NEW);
    //         service.setPrice(price);
    //         switch (serviceType) {
    //             case "smallService":
    //             case "fullService":
    //             case "otherService":
    //                 break;
    //         }
    //     }
    //     orderRepository.save(service);
    //     return service;
    // }

    public List<ReadDTOservice> getActiveOrdersByUserId(Long id) {

        List<Order> orders = orderRepository.findByUserIdAndStatus(id, Status.ACTIVE);
        Users user = userRepository.findByUserId(id);

        return orders.stream()
        .map(order -> new ReadDTOservice(order, user))
        .toList();
    }

    public List<Order> getAllDoneOrders() {

        return orderRepository.findByStatus(Status.DONE);
    }

    public List<Order> getAllNewOrders() {

        return orderRepository.findByStatus(Status.NEW);
    }
    // skończyłem tutaj poprawilem metody sortujace zamowienia po statusie
    // ponizsze metody refaktoryzowac poprzez mapper i DTO aby
    public Order saveInfoAddByUserId(Order order, Long userId) {
        List<Users> test = userService.getAllUsers();
        LocalDate nowDate = LocalDate.now();
        if (userId != 0) {
            for (Users users : test) {
                Long id = users.getUserId();
                if (id == userId) {
                    order.setAddByUser(users.getLastName() + " " + users.getUserId());
                    order.setDate(nowDate.toString());
                    order.setUserId(userId);
                    orderRepository.save(order);
                    break;
                }
            }
        }
        return order;
    }


    // te metody do reafktoryzacji gdyz zmienilem w modelu z klasy order 
    // na typ prymitywny do zapisywania przez DTO

    // public Order saveClientToOrder(Order order, Client client) {
    //     System.out.println("saveClientToOrder");
    //     if (client.getClientId() == 0) {
    //         throw new RuntimeException("no client found");
    //     } else {
    //         order.setClient(client);
    //     }
    //     orderRepository.save(order);
    //     client.setOrder(order);
    //     clientRepository.save(client);
    //     return order;
    // }

    // public Order saveBikeToOrder(Order order, Bike bike) {
    //     System.out.println("saveBikeToOrder");
    //     if (!bike.getModelType().isEmpty()) {
    //         order.setBike(bike);
    //     } else {
    //         throw new RuntimeException("no bike found");
    //     }

    //     orderRepository.save(order);
    //     bike.setOrder(order);
    //     bikeRepository.save(bike);
    //     return order;
    // }
}