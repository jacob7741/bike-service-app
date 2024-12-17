package bike.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import bike.service.app.model.repository.ServicesRepository;

public class DBinit implements CommandLineRunner {

    @SuppressWarnings("unused")
    @Autowired
    private final ServicesRepository servicesRepository;

    public DBinit(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}