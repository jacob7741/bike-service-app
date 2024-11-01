package bike.service.app.controller;

import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

    @Autowired
    ServicesRepository servicesRepository;

    @PostMapping("/services/submit")

    public String createService() {

        return "mainSite";
    }
}