package bike.service.app.controller;


import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import bike.service.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ServicesRepository servicesRepository;
    //    z response zwraca napis z metody a bez tego nazwe
    //    pliku ktory znajduje sie w templates
    //    @ResponseBody
    @GetMapping("/")
    public String home() {
        System.out.println("application start");
        return "mainSite";
    }

    @PostMapping("/services/submit")
    public String submitService(@RequestParam String serviceType, Services services) {
        orderService.createUpdateNewService(serviceType ,services);
        return "mainSite";
    }


//    @PostMapping("/services/submit")
//    public String updateService(Services services) {
//        orderService.createUpdateNewService(services);
//        System.out.println("service update");
//        return "mainSite";
//    }
}
