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


    public Order saveServiceToOrder(Services services) {
        Order order = new Order();
        System.out.println("saveServiceToOrder");

        if (services.getSmallService() == 50) {
            order.setService("small service - id: " + services.getServiceId());
        } else if (services.getFullService() == 200) {
            order.setService("full service - id: " + services.getServiceId());
        } else {
            order.setService("reprair - id: " + services.getServiceId());
        }

        orderRepository.save(order);
//        kiedy ta metoda jest włączona to za pierwszym razem zapisuje mi orderId do service
//        i vice versa lecz po pierwszym zapisie probuje nadpisac bez zmiany id.
//        TODO:błąd wynika z korzystania z jednej instancji Order wiec trzeba to opracowac inaczej
//         a w kotrolerz później będę opracowywał metodę aby wszystko sie zapisywało w jednej bazie danych
        services.setOrder(order);
        servicesRepository.save(services);
        return order;
    }

    public Order saveClientToOrder(Client client) {
        Order order = new Order();
        System.out.println("saveClientToOrder");
        if (client.getClientId() == 0) {
            throw new RuntimeException("no client found");
        } else {
            order.setClient(client.getLast_name() + " " + client.getClientId());
        }
        orderRepository.save(order);
        clientRepository.save(client);
        return order;
    }

    public Order saveBikeToOrder(Bike bike) {
        Order order = new Order();
        System.out.println("saveBikeToOrder");
        if (!bike.getModelType().isEmpty()) {
            order.setBikeModel("model of bike is: " + bike.getModelType());
        }

        orderRepository.save(order);
        bikeRepository.save(bike);
        return order;
    }
}