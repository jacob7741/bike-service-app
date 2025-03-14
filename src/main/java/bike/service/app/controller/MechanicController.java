package bike.service.app.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Order;
import bike.service.app.service.LoginService;
import bike.service.app.service.OrderService;
import bike.service.app.service.userroles.MechanicService;

@Controller
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/mechanic")
    public String mechanicSite(Model model) {

        AtomicReference<String> fullName = new AtomicReference<>(new String());

        List<Order> personalList = loginService.getPersonalList(fullName);
        List<Order> newServiceList = orderService.getAllNewOrders();

        model.addAttribute("username", fullName.get());
        model.addAttribute("orderList", personalList);
        model.addAttribute("newServiceList", newServiceList);
        return "mechanic";
    } 
    
    @GetMapping("templates/main.js")
    public String jsmain () {
        return "main.js";
    }
    
    @PostMapping("mechanic/done/{id}")
    public String doneButton(@PathVariable("id") int id) {
        mechanicService.doneStatusById(id);
            return "redirect:/mechanic";
    }

    @PostMapping("mechanic/take/{id}")
    public String takeButton(@PathVariable("id") int id) {
        mechanicService.newStatusById(id);
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
