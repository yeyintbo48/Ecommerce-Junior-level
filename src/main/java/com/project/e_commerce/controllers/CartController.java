package com.project.e_commerce.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.e_commerce.model.CartItem;
import com.project.e_commerce.model.User;
import com.project.e_commerce.repositories.UserRepository;
import com.project.e_commerce.services.CartItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartItemService cartItemService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            Principal principal) {

        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User ရှာမတွေ့ပါ"));

        CartItem cartItem = cartItemService.addToCart(user, productId, quantity);
        
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/")
    public ResponseEntity<List<CartItem>> getUserCart(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemService.getCartItems(user);
        
        return ResponseEntity.ok(cartItems);
    }
}
