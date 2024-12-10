package bike.service.app;

import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DBinit implements CommandLineRunner {

    @Autowired
    private final ServicesRepository servicesRepository;

    public DBinit(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
