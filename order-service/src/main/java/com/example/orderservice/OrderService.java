package com.example.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartClient cartClient;
    private final PaymentClient paymentClient;

    public Order createOrder(Long userId) {
        List<CartItemDto> cartItems = cartClient.getCartItems(userId);
        if (cartItems.isEmpty()) throw new RuntimeException("Корзина пуста");

        double total = cartItems.stream().mapToDouble(CartItemDto::getPrice).sum();
        List<String> names = cartItems.stream().map(CartItemDto::getProductName).toList();

        Order order = Order.builder()
                .userId(userId)
                .productNames(names)
                .totalAmount(total)
                .status(OrderStatus.CREATED)
                .build();

        return orderRepository.save(order);
    }

    public Order payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Object payment = paymentClient.pay(order.getUserId(), order.getTotalAmount());

        order.setStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}