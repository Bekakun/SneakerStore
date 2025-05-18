package com.example.orderservice;

import lombok.Data;

@Data
public class CartItemDto {
    private Long productId;
    private String productName;
    private Double price;
}