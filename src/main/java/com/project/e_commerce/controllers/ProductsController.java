package com.project.e_commerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.repositories.ProductsRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    } 

    @GetMapping("/{id}")
    public Products findById(@PathVariable Long id){
        return productsRepository.findById(id).orElse(null);
    }

    @PostMapping("add/products")
    public Products createProduct(@RequestBody Products products){
        return productsRepository.save(products);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Long id,@RequestBody Products productsDetails){
        Products products = productsRepository.findById(id).orElseThrow(()->new RuntimeException("Products not found with id:" + id));

        products.setName(productsDetails.getName());
        products.setPrice(productsDetails.getPrice());
        
        return productsRepository.save(products);
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable Long id){
        productsRepository.deleteById(id);
    }
}
