package com.project.e_commerce.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.e_commerce.model.CartItem;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>{
    List<CartItem> findByUser(User user);
    CartItem findByUserAndProduct(User user,Products product);
}
