package bike.service.app.controller;

import bike.service.app.model.Mechanic;
import bike.service.app.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Controller
public class LoginController {

    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/mechanicSite")
    public String mechanicSite(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mechanicName = authentication.getName();

        List<Mechanic> mechanicList = mechanicService.getAllMechanics();

        AtomicReference<String> fullName = new AtomicReference<>(new String());

        for (Mechanic mechanic : mechanicList) {
            if (mechanic.getUserName().equals(mechanicName)) {
               fullName.set(mechanic.getFirstName() + " " + mechanic.getLastName());
            }
        }

        model.addAttribute("username", fullName.get());
        return "mechanicSite";
    }
}