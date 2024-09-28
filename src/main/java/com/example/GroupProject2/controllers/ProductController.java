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
    ProductService productService;

    @GetMapping
    public List<ProductModel> getALlProducts(){
        return new ArrayList<>(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ProductModel getSingleProduct(@PathVariable UUID id){
        return productService.getSingleProduct(id);
    }

    @PostMapping
    public ProductModel addProduct(ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest){
    return productService.updateProduct(id,productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
    }
}
