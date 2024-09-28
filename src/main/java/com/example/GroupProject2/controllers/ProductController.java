package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.ProductModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    public List<ProductModel> getALlProducts(){
        return List.of();
    }

    @GetMapping("/{id}")
    public ProductModel getSingleProduct(@PathVariable Integer id){
        return new ProductModel();
    }

    @PostMapping
    public ProductModel addProduct(){
        return new ProductModel();
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable Integer id, @RequestBody ProductModel productModel){
    return productModel;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){

    }
}
