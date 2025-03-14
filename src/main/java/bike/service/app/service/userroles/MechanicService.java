package bike.service.app.service.userroles;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Order;
import bike.service.app.model.repository.OrderRepository;


@Service
public class MechanicService {

    @Autowired
    private OrderRepository orderRepository;

    public void newStatusById(int id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order newOrder = optional.get();
            newOrder.setStatus(Order.Status.ACTIVE);

            orderRepository.save(newOrder);
        }
    }

    public void doneStatusById(int id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order newOrder = optional.get();
            newOrder.setStatus(Order.Status.DONE);

            orderRepository.save(newOrder);
        }
    }

    public void editOrderById(String edit, int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order newOrder = orderOptional.get();
            newOrder.setService(edit);

            orderRepository.save(newOrder);
        }
    }

    public Order getOrderById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }
}
