package bike.service.app.controller;

import bike.service.app.model.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

    //odczytuje informacje z strony i wysyla żądanie do modelu service
    @GetMapping("/smallService")
    String tets() {
        return new Service().toString();
    }
}