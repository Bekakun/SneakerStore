package com.example.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/api/payments/pay")
    Object pay(@RequestParam Long userId, @RequestParam Double amount);
}