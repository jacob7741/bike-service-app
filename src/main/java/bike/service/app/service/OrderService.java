package bike.service.app.service;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Services createUpdateNewService(String serviceType, Services services) {
        System.out.println("createUpdateService");
        if (services.getServiceId() == 0) {
            switch (serviceType) {
                case "smallService":
                    services.setSmallService(50); // lub inna logika
                    break;
                case "fullService":
                    services.setFullService(200); // lub inna logika
                    break;
                case "repair":
                    services.setRepair(100); // lub inna logika
                    break;
            }
            servicesRepository.save(services);
            return services;
        } else {
            return updateExistingService(services);
        }
    }

    private Services updateExistingService(Services services) {
        Optional<Services> optionalServices = servicesRepository.findById(services.getServiceId());
        if (optionalServices.isPresent()) {
            Services newService = optionalServices.get();
            newService.setServiceId(services.getServiceId());
            newService.setSmallService(services.getSmallService());
            newService.setRepair(services.getRepair());
            return servicesRepository.save(newService);
        } else {
            return servicesRepository.save(services);
        }
    }
}