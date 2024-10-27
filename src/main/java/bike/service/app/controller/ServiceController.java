package bike.service.app.controller;

import bike.service.app.model.Services;
import bike.service.app.repository.ServicesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ServiceController {
    private ServicesRepository servicesRepository;

    @Autowired
    private ServiceController(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }
    //    odczytuje informacje z strony i wysyla żądanie do modelu service
    @GetMapping("/smallService")
    public String test(Services services) {
//        servicesRepository.findAll();
        String id = String.valueOf(services.getServiceId());
        String name = String.valueOf(services.getSmallservices());
        return id;
    }

}