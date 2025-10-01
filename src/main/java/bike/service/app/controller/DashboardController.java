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
import org.springframework.web.bind.annotation.ResponseBody;

import bike.service.app.model.Bike;
import bike.service.app.model.Client;
import bike.service.app.model.Order;
import bike.service.app.model.Posts;
import bike.service.app.model.Services;
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.service.BikeService;
import bike.service.app.service.ClientService;
import bike.service.app.service.LoginService;
import bike.service.app.service.OrderService;
import bike.service.app.service.PostsService;
import bike.service.app.service.ServicesService;
import bike.service.app.service.userroles.MechanicService;

@Controller
public class DashboardController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MechanicService mService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private MechanicService mechanicService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private ClientService clientService;
    
    
    AtomicReference<String> userFullName = new AtomicReference<>(new String());

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Order> personalList = loginService.getPersonalList(userFullName);
        List<Users> mechanicList = mService.getAllMechanics();
        List<Order> newServiceList = orderService.getAllNewOrders();
        List<Order> doneList = orderService.getAllDoneOrders();
        List<Posts> postsList = postsService.getAllPosts();

        model.addAttribute("username", userFullName.get());
        model.addAttribute("orderList", personalList);
        model.addAttribute("doneList", doneList);
        model.addAttribute("newServiceList", newServiceList);
        model.addAttribute("postsList", postsList);
        model.addAttribute("post", new Posts());
        model.addAttribute("orderList", personalList);
        model.addAttribute("mechanicList", mechanicList);
        return "dashboard";
    }

    @GetMapping("/count")
    @ResponseBody
    public int countServices() {
        return (int) orderRepository.count();
    }

    @PostMapping(value = "/services/submit", params = {"serviceType", "comment", "deliveryDate"})
    public String submitService(@RequestParam String serviceType,
            @ModelAttribute Services services,
            @ModelAttribute Order order,
            @ModelAttribute Bike bike,
            @ModelAttribute Client client) {

        servicesService.createNewService(serviceType, services);
        bikeService.addNewBike(bike);
        clientService.addNewClient(client);

        orderService.saveClientToOrder(order, client);

        orderService.saveInfoAddByUser(order, userFullName);
        orderService.saveServiceToOrder(order, services);
        orderService.saveBikeToOrder(order, bike);

        return "redirect:/dashboard";
    }

    @GetMapping("templates/script.js")
    public String jsmain() {
        return "script.js";
    }

    @PostMapping("dashboard/done/{id}")
    public String doneButton(@PathVariable("id") int id) {
        mechanicService.doneStatusById(id, userFullName);
        return "redirect:/dashboard";
    }

    @PostMapping("ć/take/{id}")
    public String takeButton(@PathVariable("id") int id) {
        mechanicService.newStatusById(id, userFullName);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/edit/{id}")
    public String getEditForm(@PathVariable("id") int id, Model model) {
        Order order = mechanicService.getOrderById(id);
        model.addAttribute("order", order);
        return "update";
    }

    @PostMapping("/orders/updateService")
    public String updateService(@RequestParam("service") String service,
            @RequestParam("orderId") int orderId) {
        mechanicService.editOrderById(service, orderId);
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/post")
    public String createPost(@RequestParam("content") String content) {
            Posts post = new Posts();
            post.setContent(content);
            postsService.createPost(post, content, userFullName);
            return "redirect:/dashboard"; 
        }
}