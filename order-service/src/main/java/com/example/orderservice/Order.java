package com.example.orderservice;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ElementCollection
    private List<String> productNames;
}