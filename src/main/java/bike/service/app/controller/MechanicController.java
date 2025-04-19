package bike.service.app.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Posts;
import bike.service.app.model.Services;
import bike.service.app.service.BikeService;
import bike.service.app.service.ClientService;
import bike.service.app.service.LoginService;
import bike.service.app.service.OrderService;
import bike.service.app.service.ServicesService;
import bike.service.app.service.userroles.MechanicService;
import bike.service.app.service.PostsService;

@Controller
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PostsService postsService;

    AtomicReference<String> fullName = new AtomicReference<>(new String());

    @GetMapping("/mechanic")
    public String mechanicSite(Model model) {

        List<Order> personalList = loginService.getPersonalList(fullName);
        List<Order> newServiceList = orderService.getAllNewOrders();
        List<Order> doneList = orderService.getAllDoneOrders();
        List<Posts> postsList = postsService.getAllPosts();

        model.addAttribute("username", fullName.get());
        model.addAttribute("orderList", personalList);
        model.addAttribute("doneList",doneList);
        model.addAttribute("newServiceList", newServiceList);
        model.addAttribute("postsList", postsList);
        model.addAttribute("post", new Posts());
        
        return "mechanic";
    }

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

        orderService.saveInfoAddByUser(order, fullName);
        orderService.saveServiceToOrder(order, services);
        orderService.saveBikeToOrder(order, bike);

        return "redirect:/mechanic";
    }

    @GetMapping("templates/script.js")
    public String jsmain() {
        return "script.js";
    }

    @PostMapping("mechanic/done/{id}")
    public String doneButton(@PathVariable("id") int id) {
        mechanicService.doneStatusById(id, fullName);
        return "redirect:/mechanic";
    }

    @PostMapping("mechanic/take/{id}")
    public String takeButton(@PathVariable("id") int id) {
        mechanicService.newStatusById(id, fullName);
        return "redirect:/mechanic";
    }

    @GetMapping("/mechanic/edit/{id}")
    public String getEditForm(@PathVariable("id") int id, Model model) {
        Order order = mechanicService.getOrderById(id);
        model.addAttribute("order", order);
        return "update";
    }

    @PostMapping("/orders/updateService")
    public String updateService(@RequestParam("service") String service,
            @RequestParam("orderId") int orderId) {
        mechanicService.editOrderById(service, orderId);
        return "redirect:/mechanic";
    }

    @PostMapping("/mechanic/post")
    public String createPost(@RequestParam("content") String content) {
            Posts post = new Posts();
            post.setContent(content);
            postsService.createPost(post, content, fullName);
            return "redirect:/mechanic"; 
        }
}