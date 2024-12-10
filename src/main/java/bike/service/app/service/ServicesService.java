package bike.service.app.service;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    public List<Services> getAllServices() {
        System.out.println("getAllServices");
        List<Services> servicesList = servicesRepository.findAll();
        if (!servicesList.isEmpty()) {
            return servicesList;
        } else {
            return new ArrayList<>();
        }
    }

    public Services getServicesById(int id) {
        System.out.println("getServicesById");
        Optional<Services> optionalServices = servicesRepository.findById(id);

        if (optionalServices.isPresent()) {
            return optionalServices.get();
        } else {
            throw new RuntimeException("servicesNotFound");
        }
    }

    public void deletedServicesById(int id) {
        System.out.println("deletedServiceById");
        Optional<Services> optionalServices = servicesRepository.findById(id);

        if (optionalServices.isPresent()) {
            servicesRepository.deleteById(id);
        } else {
            throw new RuntimeException("serviceNotDeleted");
        }
    }

    public Services createNewService(String serviceType, Services services) {
        System.out.println("createUpdateService");
        if (services.getServiceId() == 0) {
            switch (serviceType) {
                case "smallService":
                    services.setSmallService(50);// lub inna logika
                    break;
                case "fullService":
                    services.setFullService(200); // lub inna logika
                    break;
            }
            servicesRepository.save(services);
        }
        return services;
    }

    public Services createRepairService(Services reprairType, String repairName, int price) {
        System.out.println("createRepairService");
        if (reprairType.getServiceId() == 0) {
            reprairType.setRepairType(repairName);
            reprairType.setRepair(price);
            servicesRepository.save(reprairType);
        }
        return reprairType;
    }
}