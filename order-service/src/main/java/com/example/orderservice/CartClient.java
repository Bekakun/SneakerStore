package com.example.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service")
public interface CartClient {
    @GetMapping("/api/cart/user/{userId}")
    List<CartItemDto> getCartItems(@PathVariable Long userId);
}