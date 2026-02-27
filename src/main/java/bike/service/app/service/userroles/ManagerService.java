package bike.service.app.service.userroles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ClientRepository;
import bike.service.app.model.repository.OrderRepository;

@Service
public class ManagerService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BikeRepository bikeRepository;

    // public void deleteOrderById(int id) {
        
    //     Order order = orderRepository.getReferenceById(id);
    //     List<Client> clients = clientRepository.findAll();
    //     List<Bike> bikes = bikeRepository.findAll();

    //     if (order.getStatus().equals(Order.Status.DONE)) {
    //         for (Client client : clients) {
    //             client.getOrder();
    //             clientRepository.deleteById(client.getClientId());
    //         }

    //         for (Bike bike : bikes) {
    //             bike.getOrder();
    //             bikeRepository.deleteById(bike.getBikeId());
    //         }

    //         if (!(order.getOrderId() == 0)) {
    //             orderRepository.deleteById(id);
    //             System.out.println("order deleted");
    //         }
    //     }
    // }
}
