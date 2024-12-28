package bike.service.app.service.userroles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.model.Users;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.ServicesRepository;
import bike.service.app.service.UsersService;

@Service
public class MechanicService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private UsersService userService;

    public void editOrderById(String edit, int id) {
       Optional<Order> orderOptional = orderRepository.findById(id);
       if(orderOptional.isPresent()) {
        Order newOrder = orderOptional.get();
        newOrder.setService(edit);

        orderRepository.save(newOrder);

       }
    }

    public void deleteOrderById(int id) {
        Order order = orderRepository.getReferenceById(id);
        List<Client> clients = clientRepository.findAll();
        List<Bike> bikes = bikeRepository.findAll();
        List<Services> services = servicesRepository.findAll();

        for (Client client : clients) {
            clientRepository.deleteById(client.getClientId());
            client.getOrder();
        }

        for (Bike bike : bikes) {
            bike.getOrder();
            bikeRepository.deleteById(bike.getBikeId());
        }

        for (Services service : services) {
            service.getOrder();
            clientRepository.deleteById(service.getServiceId());

        }

        if (!(order.getOrderId() == 0)) {
            orderRepository.deleteById(id);
            System.out.println("order deleted");
        }
    }

    public Order saveMechanicToOrder(Order order, int id) {
        System.out.println("saveMechanicToOrder");

        Users mechanic = userService.getUserById(id);

        order.setMechanic(mechanic);
        System.out.println("Order before save: " + order);

        orderRepository.save(order);
        System.out.println("Order after save: " + order);
        // mechanicRepository.save(mechanic);
        return order;
    }

    public Order getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }
}