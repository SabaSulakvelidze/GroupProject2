package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.ProductModel;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.ProductRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private UserService userService;

    private static Map<UUID,ProductModel> products = new HashMap<UUID,ProductModel>();

    @PostConstruct
    public void init(){
        products = new HashMap<>();
        //TODO needs to add first productsz
    }

    public ProductModel addProduct(String id, ProductRequest productRequest){
        UserModel user = userService.getUserById(id);

        if (user == null) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        if (!user.getUserRole().equals(UserRole.ADMIN)) {
            throw new RuntimeException("Only admins can add products.");
        }

        ProductModel productModel = new ProductModel();
        productModel.setId(UUID.randomUUID());
        productModel.setName(productRequest.getName());
        productModel.setPrice(productRequest.getPrice());
        productModel.setQuantity(productRequest.getQuantity());
        products.put(productModel.getId(),productModel);
        return productModel;
    }

    public ProductModel updateProduct(String userId, UUID id, ProductRequest updatedProduct){
        UserModel user = userService.getUserById(userId);
        //check admin roles
        if(user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        if (!user.getUserRole().equals(UserRole.ADMIN)) {
            throw new RuntimeException("Only admins can update products.");
        }

        //check if product exists with the given id
        if(products == null) {
            throw new RuntimeException("Products not found with this ID.");
        }

        ProductModel productModel = new ProductModel();
        productModel.setName(updatedProduct.getName());
        productModel.setPrice(updatedProduct.getPrice());
        productModel.setQuantity(updatedProduct.getQuantity());

        products.put(id, productModel);
        return productModel;



         //TODO
    }

    public List<ProductModel> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    public ProductModel getSingleProduct(UUID id){
        return products.get(id);
    }

    public void deleteProduct(String userId, UUID id){
        UserModel user = userService.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        if(!user.getUserRole().equals(UserRole.ADMIN)){
            throw new RuntimeException("Only admins can delete products.");
        }

        products.remove(id);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
