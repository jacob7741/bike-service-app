package bike.service.app.controller;

import bike.service.app.config.ServicesConfig;
import bike.service.app.model.Services;

import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServicesConfig servicesConfig;

    @Autowired
    private ServicesRepository servicesRepository;

    @PostMapping("/submit")
    public String updateService(Services services, Model model) {

        Services insertService = servicesConfig.createNewService(services);
        model.addAttribute("insertService", insertService);

        return "mainSite";
    }
}
