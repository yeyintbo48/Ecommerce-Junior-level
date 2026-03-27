package com.project.e_commerce.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.e_commerce.dtos.OrderItemRequest;
import com.project.e_commerce.model.Order;
import com.project.e_commerce.services.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId,@RequestBody List<OrderItemRequest> itemRequests){
        try{
            Order savedOrder = orderService.placeOrder(userId, itemRequests);
            return new ResponseEntity<>(savedOrder,HttpStatus.CREATED);
        }catch(RuntimeException e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderHistroy(@PathVariable Long userId){
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }
}
