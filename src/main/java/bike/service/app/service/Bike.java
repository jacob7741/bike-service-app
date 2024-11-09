package bike.service.app.service;

import bike.service.app.model.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bike {
    @Autowired
    private BikeRepository bikeRepository;

    public bike.service.app.model.Bike addBike(bike.service.app.model.Bike bike) {
        System.out.println("addBike");
        if (bike.getId() == 0)
            bikeRepository.save(bike);
        return bike;
    }
}
