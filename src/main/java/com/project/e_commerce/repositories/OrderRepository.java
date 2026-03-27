package com.project.e_commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.e_commerce.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
    List<Order> findByUserId(Long userId);
}
