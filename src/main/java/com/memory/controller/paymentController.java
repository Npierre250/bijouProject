package com.memory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.memory.model.Payment;
import com.memory.service.PaymentServices;
@Controller
public class paymentController {
    @Autowired 
    private PaymentServices paymentService;
    @GetMapping(value="/admin/payment")
    public String getPaymentPage(@ModelAttribute Payment payment,Model model) {
    model.addAttribute("allPayment", paymentService.getPaymentList());
    return "admin/payment";
    }
    @PostMapping(value = "/admin/payment")
    public String CreatePayment(@ModelAttribute Payment payment) {
        paymentService.createPayment(payment);
        return "redirect:/admin/payment";
    }
    @PostMapping(value = "/admin/payment/delete")
    public String deletePayment(@ModelAttribute Payment payment) {
        paymentService.deletePayment(payment);
        return "redirect:/admin/payment";
    }
}
