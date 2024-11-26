package bike.service.app.controller;


import bike.service.app.model.*;
import bike.service.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    private MechanicService mechanicService;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("application start");
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        model.addAttribute("mechanics", mechanics);
        return "mainSite";
    }

    @RequestMapping("/services/submit")
    public String submitService(@RequestParam String serviceType,
                                @ModelAttribute Services services,
                                @ModelAttribute Order order,
                                @ModelAttribute Bike bike,
                                @ModelAttribute Client client,
                                @ModelAttribute Mechanic mechanic) {

        servicesService.createNewService(serviceType, services);
        bikeService.addNewBike(bike);
        clientService.addNewClient(client);
        orderService.saveMechanicToOrder(order, mechanic);
        orderService.saveClientToOrder(order, client);
        orderService.saveServiceToOrder(order, services);
        orderService.saveBikeToOrder(order, bike);

        return "mainSite";
    }
}
