package com.project.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.e_commerce.model.Products;
import java.util.List;


public interface ProductsRepository extends JpaRepository<Products,Long>{
    List<Products> findByNameContainingIgnoreCase(String keyword);
    List<Products> findByCategory_name(String categoryName);
    List<Products> findBy_nameContainingIgnoreCaseAndCategory_name(String keyword, String categoryName);
}
