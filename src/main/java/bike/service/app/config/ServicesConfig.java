package bike.service.app.config;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesConfig {

    @Autowired
    private ServicesRepository servicesRepository;



    public Services createNewService(Services services) {
        if (services.getServiceId() == 0) {
            services = servicesRepository.save(services);
            return services;
        } else {
            Optional<Services> service = servicesRepository.findById((long) services.getServiceId());
            if (service.isPresent()) {
                Services newServices = service.get();
                newServices = servicesRepository.save(newServices);
                return newServices;
            } else {
                services = servicesRepository.save(services);
                return services;
            }
        }
    }
}
