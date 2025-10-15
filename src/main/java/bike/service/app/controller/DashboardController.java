package bike.service.app.controller;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import bike.service.app.model.Users;
import bike.service.app.model.repository.OrderRepository;
import bike.service.app.model.repository.UsersRepository;
import bike.service.app.service.BikeService;
import bike.service.app.service.ClientService;
import bike.service.app.service.LoginService;
import bike.service.app.service.OrderService;
import bike.service.app.service.PostsService;
import bike.service.app.service.userroles.MechanicService;

@Controller
public class DashboardController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private MechanicService mechanicService;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UsersRepository userService;

    // AtomicReference<Integer> userFullName = new AtomicReference<>();
    // Integer userId;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {

        String username = authentication.getName(); // login użytkownika
        Users user = userService.findByUserName(username); // pobierz encję z bazy
        Integer userId = user.getUserId();

        model.addAttribute("clientList", clientService.getAllClients());
        model.addAttribute("username", username);
        // model.addAttribute("orderList", loginService.getPersonalList(userFullName));
        model.addAttribute("orderList", loginService.getPersonalListById(userId));
        model.addAttribute("doneList", orderService.getAllDoneOrders());
        model.addAttribute("newOrderList", orderService.getAllNewOrders());
        model.addAttribute("postsList", postsService.getAllPosts());
        model.addAttribute("post", new Posts());
        return "dashboard";
    }

    @GetMapping("/count")
    @ResponseBody
    public int countServices() {
        return (int) orderRepository.count();
    }

    @PostMapping(value = "/services/submit", params = "serviceType")
    public String submitService(@RequestParam String serviceType,
            @ModelAttribute Order service,
            @ModelAttribute Bike bike,
            @ModelAttribute Client client,
            @RequestParam String comment,
            @RequestParam String deliveryDate,
            @RequestParam(required = false) Double price,
            Authentication authentication) {

        String username = authentication.getName();
        Users user = userService.findByUserName(username);
        Integer userId = user.getUserId();

        orderService.createNewOrder(serviceType, service, comment, deliveryDate, price);
        bikeService.addNewBike(bike);
        clientService.addNewClient(client);

        orderService.saveClientToOrder(service, client);
        orderService.saveInfoAddByUserId(service, userId);
        orderService.saveBikeToOrder(service, bike);

        return "redirect:/dashboard";
    }

    @GetMapping("templates/script.js")
    public String jsmain() {
        return "script.js";
    }

    @PostMapping("dashboard/done/{id}")
    public String doneButton(@PathVariable("id") int id) {
        // mechanicService.doneStatusById(id, userFullName);
        return "redirect:/dashboard";
    }

    @PostMapping("/take/{id}")
    public String takeButton(@PathVariable("id") int id) {
        // mechanicService.newStatusById(id, userFullName);
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
        // postsService.createPost(post, content, userFullName);
        return "redirect:/dashboard";
    }
}