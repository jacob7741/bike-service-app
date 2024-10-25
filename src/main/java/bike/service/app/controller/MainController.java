package bike.service.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
//    z response zwraca napis z metody a bez tego nazwe
//    pliku ktory znajduje sie w templates
//  @ResponseBody
    public String home() {
        return "mainSite";
    }

}
