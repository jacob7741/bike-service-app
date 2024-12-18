package bike.service.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Mechanic;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.MechanicRepository;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.ServicesRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @SuppressWarnings("unused")
    @Autowired
    private MechanicRepository mechanicRepository;
    @Autowired
    private MechanicService mechanicService;

    public List<Order> getAllOrders() {
        System.out.println("get all orders");
        List<Order> ordersList = orderRepository.findAll();
        if (ordersList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return ordersList;
        }
    }

    // TODO:thinking about make it more efficient
    public void deleteOrderById(int id) {
        Order order = orderRepository.getReferenceById(id);
        List<Client> clients = clientRepository.findAll();
        List<Bike> bikes = bikeRepository.findAll();
        List<Services> services = servicesRepository.findAll();

        for (Client client : clients) {
            client.getOrder();
            clientRepository.deleteById(client.getClientId());
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

        Mechanic mechanic = mechanicService.getMechanicById(id);

        order.setMechanic(mechanic);
        System.out.println("Order before save: " + order);

        orderRepository.save(order);
        System.out.println("Order after save: " + order);
        // mechanicRepository.save(mechanic);
        return order;
    }

    public Order saveServiceToOrder(Order order, Services services) {

        System.out.println("saveServiceToOrder");

        if (services.getSmallService() == 50) {
            order.setService("small service - id: " + services.getServiceId());
        } else if (services.getFullService() == 200) {
            order.setService("full service - id: " + services.getServiceId());
        } else {
            order.setService("reprair - id: " + services.getServiceId());
        }

        orderRepository.save(order);
        services.setOrder(order);
        servicesRepository.save(services);
        return order;
    }

    public Order saveClientToOrder(Order order, Client client) {
        System.out.println("saveClientToOrder");
        if (client.getClientId() == 0) {
            throw new RuntimeException("no client found");
        } else {
            order.setClient(client.getLast_name() + " id: " + client.getClientId());
        }
        orderRepository.save(order);
        client.setOrder(order);
        clientRepository.save(client);
        return order;
    }

    public Order saveBikeToOrder(Order order, Bike bike) {
        System.out.println("saveBikeToOrder");
        if (!bike.getModelType().isEmpty()) {
            order.setBikeModel(bike.getModelType());
        }

        orderRepository.save(order);
        bike.setOrder(order);
        bikeRepository.save(bike);
        return order;
    }
}