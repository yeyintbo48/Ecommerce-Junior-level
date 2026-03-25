package com.project.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.e_commerce.model.Products;

public interface ProductsRepository extends JpaRepository<Products,Long>{

}
