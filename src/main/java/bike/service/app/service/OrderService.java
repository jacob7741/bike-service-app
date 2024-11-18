package bike.service.app.service;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.ServicesRepository;
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

    private Order order = new Order();

    public Order saveServiceToOrder(Services services) {
        System.out.println("saveServiceToOrder");
        order.setOrderId(services.getServiceId());
        if (services.getSmallService() == 50) {
            order.setService("small service - id: " + services.getSmallService());
        } else if (services.getFullService() == 200) {
            order.setService("full service - id: " + services.getServiceId());
        } else {
            order.setService("reprair - id: " + services.getServiceId());
        }

        orderRepository.save(order);
        services.setOrderId(order);
//        błąd który miałem wcześniej z podwojnym tworzeniem obiektu może się znajdować tutaj
        servicesRepository.save(services);
        return order;
    }

    public Order saveClientToOrder(Client client) {
        System.out.println("saveClientToOrder");
        if (client.getClientId() == 0) {
            throw new RuntimeException("no client found");
        } else {
            order.setClient(client.getLast_name() + " " + client.getClientId());
        }

        orderRepository.save(order);
//        i tutaj też może się tworzyć podwójny obiekt
        clientRepository.save(client);
        return order;
    }

    public Order saveBikeToOrder(Bike bike) {
        System.out.println("saveBikeToOrder");
        if (!bike.getModelType().isEmpty()) {
            order.setBikeModel("model of bike is: " + bike.getModelType());
        }

        orderRepository.save(order);
        bikeRepository.save(bike);
        return order;
    }
}