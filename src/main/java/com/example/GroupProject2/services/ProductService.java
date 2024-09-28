package com.example.GroupProject2.services;

import com.example.GroupProject2.models.entity.ProductModel;
import com.example.GroupProject2.models.request.ProductRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ProductService {

    private static HashMap<UUID,ProductModel> products;

    @PostConstruct
    public void init(){
        products = new HashMap<>();
    }

    public ProductModel addProduct(ProductRequest productRequest){
        ProductModel productModel = new ProductModel();
        productModel.setId(UUID.randomUUID());
        products.put(productModel.getId(),productModel);
        return productModel;
    }

    public ProductModel updateProduct(UUID id, ProductRequest productRequest){
        return new ProductModel(); //TODO
    }

    public Collection<ProductModel> getAllProducts(){
        return products.values();
    }

    public ProductModel getSingleProduct(UUID id){
        return products.get(id);
    }

    public void deleteProduct(UUID id){
        products.remove(id);
    }
}
