package bike.service.app.DTO;

import bike.service.app.model.Order;
import bike.service.app.model.Order.Status;

public class CreateDTOService {

    private long orderId;
    private long userId;
    private String service;
    private double price;
    private String comment;
    private long bikeId;
    private long clientId;
    private String addByUser;
    private String assignedTo;
    private String doneByUser;
    private String date;
    private String deliverDate;

    public CreateDTOService(Order order, Status status) {
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setService(service);
        order.setPrice(price);
        order.setComment(comment);
        order.setAddByUser(addByUser);;
        order.setAssignedTo(assignedTo);
        order.setDoneByUser(doneByUser);
        order.setDate(date);
        order.setDeliveryDate(deliverDate);
        order.setStatus(status);
    }
}
