package bike.service.app.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import bike.service.app.model.Order;
import bike.service.app.service.LoginService;
import bike.service.app.service.OrderService;

@Controller
public class DashboardController {

    AtomicReference<String> userFullName = new AtomicReference<>(new String());

    @Autowired
    private LoginService loginService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Order> personalList = loginService.getPersonalList(userFullName);

        return "dashboard";        
    }
}
