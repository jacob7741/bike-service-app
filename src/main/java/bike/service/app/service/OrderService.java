package bike.service.app.service;

import bike.service.app.model.*;
import bike.service.app.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Order saveMechanicToOrder(Order order, int id) {
        System.out.println("saveMechanicToOrder");

        Mechanic mechanic = mechanicService.getMechanicById(id);

        order.setMechanic(mechanic);
        System.out.println("Order before save: " + order);

        orderRepository.save(order);
        System.out.println("Order after save: " + order);
//        mechanicRepository.save(mechanic);
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