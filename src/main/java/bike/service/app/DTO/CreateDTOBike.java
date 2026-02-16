package bike.service.app.DTO;

import bike.service.app.model.Bike;
import bike.service.app.model.Order;

public class CreateDTOBike {
    private long bikeId;
    private String brand;
    private String modelType;
    private int serialNumber;

    public CreateDTOBike(Bike bike, Order order) {
        bike.setBikeId(bikeId);
        bike.setBrand(brand);
        bike.setModelType(modelType);
        bike.setSerialNumber(serialNumber);
        bike.setOrderId(order.getOrderId());
    }
}
