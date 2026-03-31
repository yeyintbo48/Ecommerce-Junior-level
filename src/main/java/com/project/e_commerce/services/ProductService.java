package com.project.e_commerce.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> searchAndFilterProduct(String keyword,String categoryName){
        if(keyword != null && categoryName != null){
            return productsRepository.findBy_nameContainingIgnoreCaseAndCategory_name(keyword, categoryName);
        }else if(keyword != null){
            return productsRepository.findByNameContainingIgnoreCase(keyword);
        }else if(categoryName != null){
            return productsRepository.findByCategory_name(categoryName);
        }else{
            return productsRepository.findAll();
        }
    }
}
