package com.project.e_commerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.project.e_commerce.model.Products;
import com.project.e_commerce.repositories.ProductsRepository;
import com.project.e_commerce.services.FileService;
import com.project.e_commerce.services.ProductService;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @GetMapping("/search")
    public ResponseEntity<Page<Products>> searchProducts(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String categoryName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ){

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Products> productsPage = productService.searchAndFilterProduct(keyword, categoryName, pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    } 

    @GetMapping("/{id}")
    public Products findById(@PathVariable Long id){
        return productsRepository.findById(id).orElseThrow(()->new RuntimeException("Products not found with id:" + id));
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

    @PostMapping("{id}/image")
    public ResponseEntity<Products> uploadProductImage(@PathVariable Long id,@RequestParam("image") MultipartFile file){
        try{
                String fileName = fileService.saveImage(file);
                Products product = productsRepository.findById(id).orElseThrow(()->new RuntimeException("Products not found with id:" + id));
                product.setImageUrl(fileName);
                Products updatedProduct = productsRepository.save(product);
                return ResponseEntity.ok(updatedProduct);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
