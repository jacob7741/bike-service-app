package bike.service.app.DTO;

import bike.service.app.model.Bike;
import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderWithUserDTO {

    private long orderId;
    private long userId;
    private int clientId;
    private long bikeId;
    private String firstName;
    private String lastName;
    private Status status;

    private String client;
    private Bike bike;
    

    public OrderWithUserDTO(Order order, Users user) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bikeId = order.getBike().getBikeId();
        this.clientId = order.getClient().getClientId();
        
        /* skończyłem tutaj */
        this.client = order.getClient().getFirst_name();
    }
    
}