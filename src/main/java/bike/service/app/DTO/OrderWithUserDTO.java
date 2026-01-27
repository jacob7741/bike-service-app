package bike.service.app.DTO;

import bike.service.app.model.Bike;
import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import lombok.Data;

@Data
public class OrderWithUserDTO {

    private long orderId;
    private long userId;
    private String client;
    private long bikeId;
    private String firstName;
    private String lastName;
    private Status status;
    

    public OrderWithUserDTO(Order order, Users user) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bikeId = order.getBike().getBikeId();
        this.client = order.getClient().getFirst_name();
        
        /* skończyłem tutaj, lecz trzeba ustawić aby przy kliencie
        były zapisane te inne dane */
        // this.client = order.getClient().getFirst_name();
    }
    
}