package com.example.paymentservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public Payment pay(@RequestParam Long userId, @RequestParam Double amount) {
        return paymentService.processPayment(userId, amount);
    }

    @GetMapping("/{userId}")
    public List<Payment> getAll(@PathVariable Long userId) {
        return paymentService.getUserPayments(userId);
    }
}