package bike.service.app.controller;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ServiceController {

    @Autowired
    ServicesRepository servicesRepository;

    @PostMapping("/services/submit")
    public String createService(@ModelAttribute Services services, Model model) {

        Services insertService = servicesRepository.save(services);
        model.addAttribute(insertService);

        return "mainSite";
    }
}