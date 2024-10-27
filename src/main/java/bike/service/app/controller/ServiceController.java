package bike.service.app.controller;

import bike.service.app.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ServiceController {

    private ServicesRepository servicesRepository;

    @Autowired
    private ServiceController(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    //odczytuje informacje z strony i wysyla żądanie do modelu service
//    @GetMapping("/smallService")
//    public String test(Model model, HttpSession httpSession) {
//        List<Services> items = servicesRepository.findAll();
//        model.addAttribute("Services", items);
//        return model.toString();
//        }

}