package com.example.cartservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public CartItem add(@RequestParam Long userId,
                        @RequestParam Long productId,
                        @RequestParam int quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/{itemId}")
    public void remove(@PathVariable Long itemId,
                       @RequestParam Long userId) {
        cartService.removeFromCart(itemId, userId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clear(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}