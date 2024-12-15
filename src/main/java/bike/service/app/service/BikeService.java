package bike.service.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bike.service.app.model.Bike;
import bike.service.app.model.repository.BikeRepository;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;
    @SuppressWarnings("unused")
    @Autowired
    private OrderService orderService;

    public Bike getBikeById(int id) {
        System.out.println("getting all bike by Id");
        Optional<Bike> optionalBike = bikeRepository.findById(id);
        if (optionalBike.isPresent())
            return optionalBike.get();
        throw new RuntimeException("no id founded");
    }
    
    public List<Bike> getAllBike() {
        System.out.println("getting all bike");
        List<Bike> bikeList = bikeRepository.findAll();
        if (!bikeList.isEmpty())
            return new ArrayList<>();
        return bikeList;
    }

    public Bike addNewBike(Bike bike) {
        System.out.println("adding bike");
        if (bike.getBikeId() == 0) {
            bikeRepository.save(bike);
        }
        bikeRepository.save(bike);
        return bike;
    }
    
    public void deletedBikeById(int id) {
        System.out.println("bike deleted...");
        Optional<Bike> optionalBike = bikeRepository.findById(id);
        if (optionalBike.isPresent())
            bikeRepository.deleteById(id);
        throw new RuntimeException("no Bike id founded");
    }
}