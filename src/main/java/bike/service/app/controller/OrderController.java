package bike.service.app.controller;


import bike.service.app.model.Services;
import bike.service.app.service.OrderService;
import bike.service.app.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    private ServicesService servicesService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home() {
        System.out.println("application start");
        return "mainSite";
    }

    @PostMapping("/services/submit")
    public String submitService(@RequestParam String serviceType, Services services) {
        servicesService.createNewService(serviceType, services);
        orderService.saveServiceToOrder(services);
        return "mainSite";
    }
}
