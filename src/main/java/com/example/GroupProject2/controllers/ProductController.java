package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.ProductModel;
import com.example.GroupProject2.models.request.ProductRequest;
import com.example.GroupProject2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all-products")
    public List<ProductModel> getALlProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getSingleProduct(@PathVariable UUID id){
        return productService.getSingleProduct(id);
    }

    @PostMapping("/add/{userId}")
    public ProductModel addProduct(@PathVariable String userId,@RequestBody ProductRequest productRequest){
        return productService.addProduct(userId, productRequest);
    }

    @PutMapping("update/{userId}/{id}")
    public ProductModel updateProduct(@PathVariable String userId, @PathVariable UUID id, @RequestBody ProductRequest productRequest){
    return productService.updateProduct(userId, id, productRequest);
    }

    @DeleteMapping("/{userId}/{id}")
    public void deleteProduct(@PathVariable String userId, @PathVariable UUID id){
        productService.deleteProduct(userId, id);
    }
}
