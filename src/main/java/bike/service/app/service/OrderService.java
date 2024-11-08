package bike.service.app.service;

import bike.service.app.model.Bike;
import bike.service.app.model.Services;
import bike.service.app.model.repository.BikeRepository;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ServicesRepository servicesRepository;
//    @Autowired
//    private BikeRepository bikeRepository;
    //w tej metodzie moge opreacowac dokladnie jaki serwis ma sie dodawca i gdzi dokladnie
    //pierwsza wersje ktora pisze to jet logi ktora zpisuje serwis do tabeli serwisow
    //a docelowy koncept jest taki aby pojedyncze serwisy wybrane z tabeli byly zapisywane do
    //tabeli orders wraz z danymi mechanika oraz klienta

//    public Bike addBike(Bike bike) {
//        System.out.println("addBike");
//        bike = bikeRepository.save(bike);
//        return bike;
//    }

    public Services createUpdateNewService(Services services) {
        System.out.println("createUpdateService");
//        services = servicesRepository.save(services);
//        return services;
        if (services.getServiceId() == 0) {
            services = servicesRepository.save(services);
            return services;
        } else {
            Optional<Services> optionalServices = servicesRepository.findById(services.getServiceId());
            if (optionalServices.isPresent()) {
                Services newService = optionalServices.get();
                newService.setServiceId(services.getServiceId());
                newService.setSmallService(services.getSmallService());
                newService = servicesRepository.save(newService);
                return newService;
            } else {
                services = servicesRepository.save(services);
                return services;
            }
        }
    }
}