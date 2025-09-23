package bike.service.app.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;
import bike.service.app.service.LoginService;
import bike.service.app.service.userroles.MechanicService;

@Controller
public class DashboardController {

    AtomicReference<String> userFullName = new AtomicReference<>(new String());

    @Autowired
    private LoginService loginService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MechanicService mService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Order> personalList = loginService.getPersonalList(userFullName);
        List<Users> mechanicList = mService.getAllMechanics();
        model.addAttribute("orderList", personalList);
        model.addAttribute("mechanicList", mechanicList);
        return "dashboard";
    }



    @GetMapping("/count")
    @ResponseBody
    public int countServices() {
        return (int) orderRepository.count();
    }
}