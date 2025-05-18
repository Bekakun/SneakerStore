package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Order create(@RequestParam Long userId) {
        return orderService.createOrder(userId);
    }

    @PostMapping("/pay/{orderId}")
    public Order pay(@PathVariable Long orderId) {
        return orderService.payOrder(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getByUser(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @GetMapping("/{orderId}")
    public Order getById(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
}