package com.project.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.e_commerce.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
