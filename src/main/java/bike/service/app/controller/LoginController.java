package bike.service.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Mechanic;
import bike.service.app.model.Order;
import bike.service.app.service.MechanicService;
import bike.service.app.service.OrderService;

@Controller
public class LoginController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/mechanicSite")
    public String mechanicSite(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mechanicName = authentication.getName();

        List<Mechanic> mechanicList = mechanicService.getAllMechanics();
        List<Order> orderList = orderService.getAllOrders();
        AtomicReference<String> fullName = new AtomicReference<>(new String());

        List<Order> personalList = new ArrayList<>();

        // TODO: this method move to loginService think how to make more efficient
        for (Mechanic mechanic : mechanicList) {
            if (mechanic.getUserName().equals(mechanicName)) {
                fullName.set(mechanic.getFirstName() + " " + mechanic.getLastName());
                for (Order order : orderList) {
                    if (order.getMechanic().getLastName().equals(mechanic.getLastName())) {
                        personalList.add(order);
                    }
                }
            }
        }
        model.addAttribute("username", fullName.get());
        model.addAttribute("orderList", personalList);
        return "mechanicSite";
    }

    @GetMapping("/done/{id}")
    public String doneButton(
            @RequestParam int id) {
        orderService.deleteOrderById(id);
        return "redirect:/mechanicSite";
    }
    @GetMapping("/edit/{id}")
    public String editButton(
            @RequestParam int id) {
        orderService.deleteOrderById(id);
        return "redirect:/mechanicSite";
    }
}