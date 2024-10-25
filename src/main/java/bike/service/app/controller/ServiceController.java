package bike.service.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

    @GetMapping("/smallService")
    public String test() {
        return "Działa";
    }
}
