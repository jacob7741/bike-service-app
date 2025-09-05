package bike.service.app.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;

@Controller
public class ManagerController {

    private Services services;

    private ServicesRepository servicesRepository;
    @GetMapping("/manager")
    public String managerSite(Model model) {

        model.addAttribute("chartData", getNumberOfServices());

        return "manager";
    }

    private int getNumberOfServices() {
        return (int) servicesRepository.count();
    }
}
