package bike.service.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ManagerController {

    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public String managerSite() {
        return "manager";
    }
}
