package bike.service.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Order;
import bike.service.app.model.Users;
import bike.service.app.service.OrderService;
import bike.service.app.service.UsersService;
import bike.service.app.service.userroles.MechanicService;

@Controller
public class MechanicController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UsersService userService;
    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/mechanic")
<<<<<<< HEAD
    @PreAuthorize("hasRole('MECHANIC')")
=======
    // @PreAuthorize("hasRole('MECHANIC')")
>>>>>>> refactor
    public String mechanicSite(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mechanicName = authentication.getName();

        List<Users> usersList = userService.getAllUsers();
        List<Order> orderList = orderService.getAllActiveOrders();
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
        return "mechanic";
    }

    @PostMapping("/mechanic/done/{id}")
    public String doneButton(
            @PathVariable("id") int id) {
        mechanicService.doneStatusById(id);
        return "redirect:/mechanic";
    }

    @GetMapping("/mechanic/edit/{id}")
    public String getEditForm(@PathVariable("id") int id, Model model) {
        Order order = mechanicService.getOrderById(id);
        model.addAttribute("order", order);
        return "update";
    }

    @PostMapping("/orders/updateService")
    public String updateService(@RequestParam("service") String service,
            @RequestParam("orderId") int orderId) {
        mechanicService.editOrderById(service, orderId);
        return "redirect:/mechanic";
    }
}
