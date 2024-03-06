package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/detail")
    public String getPaymentDetailForm() {
        return "paymentDetailForm";
    }

    @GetMapping("/payment/detail/{paymentId}")
    public String getPaymentDetailById(@PathVariable String paymentId, Model model) {
        Payment payment = paymentService.getPayment(paymentId);
        model.addAttribute("payment", payment);
        return "paymentDetail";
    }

    @GetMapping("/payment/admin/list")
    public String getPaymentsAdminList(Model model) {
        List<Payment> payments = paymentService.getAllPayment();
        model.addAttribute("payments", payments);
        return "paymentListAdmin";
    }

}
