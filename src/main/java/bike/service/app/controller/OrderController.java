package bike.service.app.controller;


import bike.service.app.model.Bike;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.service.BikeService;
import bike.service.app.service.OrderService;
import bike.service.app.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home() {
        System.out.println("application start");
        return "mainSite";
    }

    @RequestMapping("/services/submit")
    public String submitService(@RequestParam String serviceType,
                                @ModelAttribute Services services,
                                @ModelAttribute Order order,
                                @ModelAttribute Bike bike) {
        servicesService.createNewService(serviceType, services);
        bikeService.addNewBike(bike);
        orderService.saveServiceToOrder(order, services);
        orderService.saveBikeToOrder(order, bike);
        return "mainSite";
    }
}
