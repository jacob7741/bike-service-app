package bike.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Mechanic;
import bike.service.app.model.Order;
import bike.service.app.model.Services;
import bike.service.app.service.BikeService;
import bike.service.app.service.ClientService;
import bike.service.app.service.MechanicService;
import bike.service.app.service.OrderService;
import bike.service.app.service.ServicesService;

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

    @PostMapping(value = "/services/submit", params = "serviceType")
    public String submitService(@RequestParam String serviceType,
                                @ModelAttribute Services services,
                                @ModelAttribute Order order,
                                @ModelAttribute Bike bike,
                                @ModelAttribute Client client,
                                @RequestParam int mechanics,
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

        orderService.saveMechanicToOrder(order, mechanics);
        orderService.saveClientToOrder(order, client);
        orderService.saveServiceToOrder(order, services);
        orderService.saveBikeToOrder(order, bike);

        return "redirect:/";
    }
}