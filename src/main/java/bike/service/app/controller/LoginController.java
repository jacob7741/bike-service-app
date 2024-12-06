package bike.service.app.controller;

import bike.service.app.model.Mechanic;
import bike.service.app.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/mainSite")
    public String mainSite(Model model) {
        System.out.println("application start");
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        model.addAttribute("mechanics", mechanics);
        return "mainSite";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "mechanicSite";
    }

    @GetMapping(value = "/mechanicSite")
    public String mechanicSite() {
        return "mechanicSite";
    }

}