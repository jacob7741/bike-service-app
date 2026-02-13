package bike.service.app.DTO;

import bike.service.app.model.Client;
import bike.service.app.model.Order;

public class CreateDTOClient {
    
    private long clientId;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;

    public CreateDTOClient(Client client, Order order) {
        client.setClientId(clientId);
        client.setFirst_name(first_name);
        client.setLast_name(last_name);
        client.setPhoneNumber(phone_number);
        client.setEmail(email);
        client.setOrderId(order.getOrderId());
    }
}
