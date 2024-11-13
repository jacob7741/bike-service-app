package bike.service.app.service;

import bike.service.app.model.Order;
import bike.service.app.model.Services;
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

    public Order saveServiceToOrder(Services services) {
        Order order = new Order();
        order.setServices(services);
        order.setService("e");
        order.setMechanic("w");
        order.setBikeModel("q");
        return orderRepository.save(order);
    }
}
