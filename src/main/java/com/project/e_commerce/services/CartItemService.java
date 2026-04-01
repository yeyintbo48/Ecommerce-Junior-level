package com.project.e_commerce.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.project.e_commerce.model.CartItem;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.model.User;
import com.project.e_commerce.repositories.CartItemRepository;
import com.project.e_commerce.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ProductsRepository productsRepository;
    private final CartItemRepository cartItemRepository;

    public CartItem addToCart(User user,Long productId,Integer quantity){
        Products product = productsRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cartItemRepository.findByUserAndProduct(user, product);
        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartItemRepository.save(existingItem);
        }else{
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
        }
    }

    public List<CartItem> getCartItems(User user){
        return cartItemRepository.findByUser(user);
    }
}
