package bike.service.app.controller;

import bike.service.app.model.*;
import bike.service.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    mam dwie metody które mapują home druga jest w logincotrolerze
//    dowiedzieć się więcej czy musi tak to być czy jest jakieś
//    bardziej wydajne i poprawne rozwiazanie

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("application start");
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        model.addAttribute("mechanics", mechanics);
        return "mainSite";
    }

    @RequestMapping(value = "/services/submit", method = RequestMethod.POST, params = "serviceType")
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