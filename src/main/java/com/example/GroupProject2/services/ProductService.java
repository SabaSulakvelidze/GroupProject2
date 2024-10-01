package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.ProductModel;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import com.example.GroupProject2.models.request.ProductRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    UserService userService;

    private static HashMap<Integer, ProductModel> products;
    int index = 0;
    @PostConstruct
    public void init() {
        products = new HashMap<>();
        ProductModel productModel1 = new ProductModel(index++, "product1", 100.00, 1);
        ProductModel productModel2 = new ProductModel(index++, "product2", 200.00, 2);
        ProductModel productModel3 = new ProductModel(index++, "product3", 500.00, 5);
        products.put(productModel1.getId(), productModel1);
        products.put(productModel2.getId(), productModel2);
        products.put(productModel3.getId(), productModel3);
        //TODO needs to add first products

    }

    public ProductModel addProduct(Integer userId, UserRole userRole, ProductRequest productRequest) {

        validateUser(userId, userRole);

        ProductModel productModel = new ProductModel();
        productModel.setId(index++);
        productModel.setName(productRequest.getName());
        productModel.setPrice(productRequest.getPrice());
        productModel.setQuantity(productRequest.getQuantity());
        products.put(productModel.getId(), productModel);
        return productModel;
    }

    public ProductModel updateProduct(Integer userId, UserRole userRole, Integer id, ProductRequest newProductRequest) {
        validateUser(userId, userRole);
        ProductModel productModel = products.get(id);
        if (productModel == null) throw new RuntimeException("Product not found with ID: " + id);
        productModel.setName(newProductRequest.getName());
        productModel.setPrice(newProductRequest.getPrice());
        productModel.setQuantity(newProductRequest.getQuantity());
        return productModel;
    }

    public List<ProductModel> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public ProductModel getSingleProduct(Integer id) {
        return products.get(id);
    }

    public void deleteProduct(Integer userId, UserRole userRole, Integer id) {
        validateUser(userId, userRole);
        products.remove(id);
    }

    public void validateUser(Integer userId, UserRole userRole) {
        UserModel user = userService.getSingleUser(userId);

        if (user == null) throw new RuntimeException("User not found with ID: " + userId);

        if (!user.getUserRole().equals(userRole)) throw new RuntimeException("User role is incorrect");

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new RuntimeException("User does not have permission, not admin");
    }
}
