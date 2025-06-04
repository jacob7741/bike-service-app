package bike.service.app.controller;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    AtomicReference<String> userFullName = new AtomicReference<>(new String());

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        

        return "dashboard";        
    }
}
