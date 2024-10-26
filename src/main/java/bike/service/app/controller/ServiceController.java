package bike.service.app.controller;

import bike.service.app.model.Services;
import bike.service.app.repository.ServicesRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

    private ServicesRepository servicesRepository;

    private ServiceController(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    //odczytuje informacje z strony i wysyla żądanie do modelu service
    @GetMapping("/smallService")
    @ResponseBody
    public String test(Services services) {
        return "Works";
    }
}