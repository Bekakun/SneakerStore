package com.example.cartservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public CartItem addToCart(Long userId, Long productId, int quantity) {
        productClient.getProductById(productId);
        CartItem item = CartItem.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .build();
        return cartRepository.save(item);
    }

    public List<CartItem> getUserCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeFromCart(Long id, Long userId) {
        CartItem item = cartRepository.findById(id).orElseThrow();
        if (!item.getUserId().equals(userId)) throw new RuntimeException("Access denied");
        cartRepository.deleteById(id);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}