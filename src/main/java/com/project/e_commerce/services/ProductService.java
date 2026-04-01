package com.project.e_commerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private ProductsRepository productsRepository;

    public Page<Products> searchAndFilterProduct(String keyword,String categoryName,Pageable pageable){
        if(keyword != null && categoryName != null){
            return productsRepository.findByNameContainingIgnoreCaseAndCategory_Name(keyword, categoryName, pageable);
        }else if(keyword != null){
            return productsRepository.findByNameContainingIgnoreCase(keyword,pageable);
        }else if(categoryName != null){
            return productsRepository.findByCategory_Name(categoryName,pageable);
        }else{
            return productsRepository.findAll(pageable);
        }
    }
}
