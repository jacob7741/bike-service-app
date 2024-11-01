package bike.service.app.controller;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

    @Autowired
    ServicesRepository servicesRepository;

    @PostMapping("/register")
    @ResponseBody
    public String createService(@ModelAttribute Services services, Model model) {
        System.out.println(services.getServiceId());
        System.out.println(services.getSmallservices());

        Services services1 = servicesRepository.save(services);
        model.addAttribute(services1.getSmallservices());
        return "services";
    }
}