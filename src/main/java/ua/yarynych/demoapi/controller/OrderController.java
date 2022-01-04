package ua.yarynych.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.yarynych.demoapi.model.Bar;
import ua.yarynych.demoapi.model.Menu;
import ua.yarynych.demoapi.model.Order;
import ua.yarynych.demoapi.repository.BarRepository;
import ua.yarynych.demoapi.repository.MenuRepository;
import ua.yarynych.demoapi.repository.OrderRepository;
import ua.yarynych.demoapi.service.OrderService;
import ua.yarynych.demoapi.service.WorkerService;


import java.util.ArrayList;
import java.util.Optional;

@Controller
public class OrderController {

    ArrayList<Menu> orderMenu = new ArrayList<>();
    ArrayList<Bar> orderBar = new ArrayList<>();

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    BarRepository barRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    WorkerService workerService;


    @GetMapping("/menuOrder")
    public String orderPage(Model model) {
        model.addAttribute("title", "Сторінка замовлення");
        model.addAttribute("menu", orderMenu);
        model.addAttribute("bar", orderBar);
        model.addAttribute("total_price", orderService.countCost(orderBar, orderMenu));
        return "orderMenu";
    }

    @PostMapping("/makeMenuOrder/{id}")
    public String makeMenuOrder(@PathVariable(value = "id") long id, Model model) {
        Optional<Menu> menu = menuRepository.findById(id);
        menu.ifPresent(orderMenu::add);
        return "redirect:/menuOrder";
    }

    @PostMapping("/makeBarOrder/{id}")
    public String makeBarOrder(@PathVariable(value = "id") long id, Model model) {
        Optional<Bar> bar = barRepository.findById(id);
        bar.ifPresent(orderBar::add);
        return "redirect:/menuOrder";
    }

    @GetMapping("/orderDone")
    public String orderDone() {
        return "orderDone";
    }

    @PostMapping("/order")
    public String doOrder() {

        for(Menu dish: orderMenu) {
            dish.setPopularity(dish.getPopularity() + 1);
            menuRepository.save(dish);
        }
        for (Bar drink: orderBar) {
            drink.setPopularity(drink.getPopularity() + 1);
            barRepository.save(drink);
        }

        orderBar.clear();
        orderMenu.clear();

        return "redirect:/orderDone";
    }

    @GetMapping("/orders")
    public String order(Model model) {
        model.addAttribute("title", "Усі замовлення");
        Iterable<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }
}
