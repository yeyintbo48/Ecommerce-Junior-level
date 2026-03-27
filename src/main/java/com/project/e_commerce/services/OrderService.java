package com.project.e_commerce.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.project.e_commerce.dtos.OrderItemRequest;
import com.project.e_commerce.model.Order;
import com.project.e_commerce.model.OrderItem;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.model.User;
import com.project.e_commerce.repositories.OrderRepository;
import com.project.e_commerce.repositories.ProductsRepository;
import com.project.e_commerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductsRepository productsRepository;

    public List<Order> getOrdersByUser(Long userId){
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order placeOrder(Long userId,List<OrderItemRequest> itemRequests){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with useId" + userId));

        Order order= new Order();
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemRequest request: itemRequests){
            Products product = productsRepository.findById(request.getProductId()).orElseThrow(()->new RuntimeException("Product not found with id:" + request.getProductId()));
            OrderItem item = new OrderItem();
            item.setProducts(product);
            item.setQuantity(request.getQuantity());

            item.setPrice(product.getPrice());
            item.setOrder(order);

            orderItems.add(item);
        }
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }
}
