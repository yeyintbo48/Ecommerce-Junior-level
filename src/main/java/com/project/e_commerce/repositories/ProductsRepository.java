package com.project.e_commerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.e_commerce.model.Products;


public interface ProductsRepository extends JpaRepository<Products,Long>{
    Page<Products> findByNameContainingIgnoreCase(String keyword,Pageable pageable);
    Page<Products> findByCategory_Name(String categoryName,Pageable pageable);
    Page<Products> findByNameContainingIgnoreCaseAndCategory_Name(String keyword, String categoryName,Pageable pageable);
}
