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
            services.getServiceId();
            servicesRepository.save(services);
            return services;
    }
}