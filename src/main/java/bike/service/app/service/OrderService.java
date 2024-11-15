package bike.service.app.service;

import bike.service.app.model.Bike;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.model.repository.BikeRepository;
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

    private Order order = new Order();

    public Order saveServiceToOrder(Services services) {
        System.out.println("saveServiceToOrder");
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

    public Order saveBikeToOrder(Bike bike) {
        System.out.println("saveBikeToOrder");
        if (!bike.getModelType().isEmpty()) {
            order.setBikeModel("model of bike is: " + bike.getModelType());
        }

        orderRepository.save(order);
        bike.setModelType(order.getBikeModel());

        bikeRepository.save(bike);
        return order;
    }
}