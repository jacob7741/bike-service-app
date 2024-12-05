package bike.service.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/mainSite")
    public String mainSite() {
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