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
        if (services.getSmallService() == 50) {
            order.setService("small service - id: " + services.getServiceId());
        } else if (services.getFullService() == 200) {
            order.setService("full service - id: " + services.getServiceId());
        } else {
            order.setService("reprair - id: " + services.getServiceId());
        }



        orderRepository.save(order);
        services.setOrderId(order);

        servicesRepository.save(services);
        return order;
    }

}