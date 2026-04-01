package com.project.e_commerce.dtos;

import java.util.List;
import com.project.e_commerce.model.CartItem;
import lombok.Data;

@Data
public class CartDto {
    private List<CartItem> cartItems;
    private Double totalPrice;
}
