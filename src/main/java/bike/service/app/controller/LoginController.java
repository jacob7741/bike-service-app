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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.service.OrderService;
import bike.service.app.service.UsersService;

@Controller
public class LoginController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersService userService;

    @GetMapping("/users")
    public String mechanicSite(Model model) {                   

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mechanicName = authentication.getName();

        List<Users> usersList = userService.getAllUsers();
        List<Order> orderList = orderService.getAllOrders();
        AtomicReference<String> fullName = new AtomicReference<>(new String());

        List<Order> personalList = new ArrayList<>();

        // TODO: this method move to loginService think how to make more efficient
        for (Users user : usersList) {
            if (user.getUserName().equals(mechanicName)) {
                fullName.set(user.getFirstName() + " " + user.getLastName());
                for (Order order : orderList) {
                    if (order.getMechanic().getLastName().equals(user.getLastName())) {
                        personalList.add(order);
                    }
                }
            }
        }
        model.addAttribute("username", fullName.get());
        model.addAttribute("orderList", personalList);
        return "users";
    }

    @GetMapping("/users/done/{id}")
    public String doneButton(
            @PathVariable("id") int id) {
        orderService.deleteOrderById(id);
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String editButton(
            @RequestParam int id) {
        orderService.deleteOrderById(id);
        return "redirect:/users";
    }
}