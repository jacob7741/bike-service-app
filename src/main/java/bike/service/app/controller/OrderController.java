package bike.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.service.BikeService;
import bike.service.app.service.ClientService;
import bike.service.app.service.OrderService;
import bike.service.app.service.ServicesService;
import bike.service.app.service.UsersService;

@Controller
public class OrderController {
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UsersService userService;

    // @GetMapping("/order")
    // public String home(Model model) {
    //     System.out.println("application start");
    //     List<Users> users = userService.getAllUsers();
    //     model.addAttribute("users", users);
    //     return "order";
    // }

    @PostMapping(value = "/services/submit", params = "serviceType")
    public String submitService(@RequestParam String serviceType,
                                @ModelAttribute Services services,
                                @ModelAttribute Order order,
                                @ModelAttribute Bike bike,
                                @ModelAttribute Client client,
                                @RequestParam(required = false) String repairDetails,
                                @RequestParam(required = false) Integer repairPrice) {

        if ("repair".equals(serviceType)) {
            servicesService.createRepairService(services, repairDetails, repairPrice);
        } else {
            servicesService.createNewService(serviceType, services);
        }

        servicesService.createNewService(serviceType, services);
        bikeService.addNewBike(bike);
        clientService.addNewClient(client);

        orderService.saveClientToOrder(order, client);
        orderService.saveServiceToOrder(order, services);
        orderService.saveBikeToOrder(order, bike);

        return "redirect:/mechanic";
    }
}