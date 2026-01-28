package bike.service.app.DTO;

import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;
import bike.service.app.model.Users;
import lombok.Data;

@Data
public class OrderWithUserDTO {

    private long orderId;
    private long userId;
    private String bike;
    private String client;
    private String firstName;
    private String lastName;
    private Status status;
    private String date;
    private String deliveryDate;
    
    

    public OrderWithUserDTO(Order order, Users user) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bike = order.getBike().getModelType() + " - " + order.getBike().getBrand();
        this.client = order.getClient().getFirst_name() + " " + order.getClient().getLast_name();
        this.date = order.getDate();
        this.deliveryDate = order.getDeliveryDate();

        
        /* skończyłem tutaj, lecz trzeba ustawić aby przy kliencie
        były zapisane te inne dane */
        // this.client = order.getClient().getFirst_name();
    }
    
}