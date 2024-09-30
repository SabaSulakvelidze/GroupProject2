package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.Enum.UserRole;
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
    public List<ProductModel> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getSingleProduct(@PathVariable UUID id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping
    public ProductModel addProduct(@RequestParam Integer userId, @RequestParam UserRole userRole, @RequestBody ProductRequest productRequest) {
        return productService.addProduct(userId, userRole, productRequest);
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@RequestParam Integer userId, @RequestParam UserRole userRole, @PathVariable UUID id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(userId, userRole, id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@RequestParam Integer userId, @RequestParam UserRole userRole, @PathVariable UUID id) {
        productService.deleteProduct(userId, userRole, id);
    }
}
