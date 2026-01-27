package bike.service.app.DTO;

import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.Order.Status;
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
    private long client;
    private long bike;
    private String firstName;
    private String lastName;
    private Status status;

    public OrderWithUserDTO(Order order, Users user) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bike = order.getBike() != null ? order.getBike().getBikeId() : null;
        this.client = order.getClient() != null ? order.getClient().getClientId() : null;
    }
    
    /* i dalej trzeba utworzyc metody w order sevcie które 
    będą obsłyugiwać DTO najlepiej jest to zrobić przy uzyciu
    klasy mapper */
}