package bike.service.app.service.userroles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;
import bike.service.app.service.OrderService;
import bike.service.app.service.UsersService;

@Service
public class MechanicService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService oService;
    @Autowired
    private UsersService userService;
    @Autowired
    private UsersRepository usersRepository;

    private LocalDate nowDate = LocalDate.now();
    public void newStatusById(int id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order newOrder = optional.get();
            if (newOrder.getStatus().equals(Status.NEW)) {
                newOrder.setStatus(Order.Status.ACTIVE);
                newOrder.setDate(nowDate.toString());
                oService.saveUserToOrder(newOrder, id);
            }
            orderRepository.save(newOrder);
        }
    }

    public void doneStatusById(int id, AtomicReference<String> fullName) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order newOrder = optional.get();
            newOrder.setStatus(Order.Status.DONE);
            newOrder.setDate(nowDate.toString());
            List<Users> lmechanics = userService.getAllUsers();

            for (Users user : lmechanics) {
                if ((user.getFirstName() + " " + user.getLastName()).equals(fullName.get())) {
                    newOrder.setDoneByUser(user.getLastName());;
                }
                orderRepository.save(newOrder);
            }
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

    public List<Users> getAllMechanics() {
        return usersRepository.findAll();
    }
}