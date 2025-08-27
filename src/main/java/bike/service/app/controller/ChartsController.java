package bike.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bike.service.app.model.Services;
import bike.service.app.model.repository.ServicesRepository;

@Controller
@RequestMapping("/dashboard")
public class ChartsController {

    @Autowired
    private ServicesRepository serviceDataRepository;

    @GetMapping
    public List<Services> getChartData () {

        return serviceDataRepository.findAll();
    }
}
