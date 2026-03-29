package com.project.e_commerce.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.e_commerce.dtos.OrderItemRequest;
import com.project.e_commerce.dtos.OrderRequest;
import com.project.e_commerce.model.Order;
import com.project.e_commerce.model.OrderStatus;
import com.project.e_commerce.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId,@Valid @RequestBody OrderRequest request){
            Order savedOrder = orderService.placeOrder(userId, request.getItems());
            return new ResponseEntity<>(savedOrder,HttpStatus.CREATED);
        }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderHistroy(@PathVariable Long userId){
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long orderId,@RequestParam OrderStatus status){
        Order updateOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updateOrder);
    }
}
