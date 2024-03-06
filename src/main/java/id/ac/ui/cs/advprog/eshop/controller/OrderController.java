package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public String createOrderForm() {
        return "createOrderForm";
    }

    @GetMapping("/order/history")
    public String historyForm() {
        return "historyForm";
    }

    @PostMapping("/order/history")
    public String showHistory(@RequestParam("name") String author, Model model) {
        List<Order> orders = orderService.findAllByAuthor(author);
        model.addAttribute("orders", orders);
        return "orderHistory";
    }

    @GetMapping("/order/pay/{orderId}")
    public String showPaymentPage(@PathVariable("orderId") String orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            model.addAttribute("order", order);
            return "paymentOrderPage";
        }
        return "orderNotFound";
    }

    @PostMapping("/order/pay/{orderId}")
    public String processPayment(@PathVariable("orderId") String orderId, Model model) {
        Order order = orderService.updateStatus(orderId, "PAID");
        if (order != null) {
            model.addAttribute("paymentId", order.getId());
            return "paymentSuccess";
        }
        return "paymentFailed";
    }
}
