package bike.service.app.controller;

import bike.service.app.config.ServicesConfig;
import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    //    z response zwraca napis z metody a bez tego nazwe
//    pliku ktory znajduje sie w templates
//  @ResponseBody
    @GetMapping("/")
    public String home() {
        System.out.println("application start");
        return "mainSite";
    }
        @Autowired
        private ServicesConfig servicesConfig;

        @Autowired
        private ServicesRepository servicesRepository;

        @PostMapping("/services/submit")
        public String updateService(Services services, Model model) {

            Services insertService = servicesConfig.createNewService(services);
            model.addAttribute("insertService", insertService);
            System.out.println("service update");
            return "mainSite";

    }
}
