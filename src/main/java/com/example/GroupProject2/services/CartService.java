package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.CartItemModel;
import com.example.GroupProject2.models.entity.ProductModel;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    private static HashMap<UUID, CartItemModel> cart;

    @PostConstruct
    public void init() {
        cart = new HashMap<>();
    }

    public CartItemModel addInCart(Integer userId, UserRole userRole, CartItemRequest cartItemRequest) {
        validateUser(userId, userRole);

        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setId(UUID.randomUUID());
        cartItemModel.setProductId(cartItemRequest.getProductId());
        cartItemModel.setUserId(cartItemRequest.getUserId());
        cartItemModel.setQuantityInCart(cartItemRequest.getQuantityInCart());
        cart.put(cartItemModel.getId(), cartItemModel);
        return cartItemModel;
    }

    public List<CartItemModel> getAllCartItems(Integer userId) {
        List<CartItemModel> cartItemsByUser = new ArrayList<>(cart.values());
        cartItemsByUser.removeIf(item -> !Objects.equals(item.getUserId(), userId));
        return cartItemsByUser;
    }

    public CartItemModel updateCartItem(Integer userId, UserRole userRole, UUID itemId, CartItemRequest newCartItemRequest) {
        validateUser(userId, userRole);
        CartItemModel cartItemModel = cart.get(itemId);
        if (cartItemModel == null) throw new RuntimeException("Item in Cart not found with ID: " + itemId);
        cartItemModel.setProductId(newCartItemRequest.getProductId());
        cartItemModel.setUserId(newCartItemRequest.getUserId());
        cartItemModel.setQuantityInCart(newCartItemRequest.getQuantityInCart());
        return cartItemModel;
    }

    public void removeFromCart(Integer userId, UserRole userRole, UUID itemId) {
        validateUser(userId, userRole);
        cart.remove(itemId);
    }

    public void validateUser(Integer userId, UserRole userRole) {
        UserModel user = userService.getSingleUser(userId);

        if (user == null) throw new RuntimeException("User not found with ID: " + userId);

        if (!user.getUserRole().equals(userRole)) throw new RuntimeException("User role is incorrect");

        if (!user.getUserRole().equals(UserRole.USER))
            throw new RuntimeException("User does not have permission, not admin");
    }

    public void reduceBudget(CartItemRequest cartItemRequest) {
        UserModel userModel = userService.getSingleUser(cartItemRequest.getUserId());
        Double price = productService.getSingleProduct(cartItemRequest.getProductId()).getPrice();
        if (userModel.getBudget() < price * cartItemRequest.getQuantityInCart())
            throw new RuntimeException("User does not have enough budget");
        userModel.setBudget(userModel.getBudget() - price * cartItemRequest.getQuantityInCart());
    }

    public void increaseBudgetAndQuantity(UUID itemId) {
        CartItemModel cartItemModel = cart.get(itemId);
        UserModel singleUser = userService.getSingleUser(cartItemModel.getUserId());
        ProductModel singleProduct = productService.getSingleProduct(cartItemModel.getProductId());

        singleUser.setBudget(singleUser.getBudget() + singleProduct.getPrice() * cartItemModel.getQuantityInCart());
        singleProduct.setQuantity(singleProduct.getQuantity()+ cartItemModel.getQuantityInCart());
    }

    public void reduceProductQuantity(CartItemRequest cartItemRequest) {
        ProductModel productModel = productService.getSingleProduct(cartItemRequest.getProductId());
        if (productModel.getQuantity() < cartItemRequest.getQuantityInCart())
            throw new RuntimeException("There are not enough items");
        productModel.setQuantity(productModel.getQuantity() - cartItemRequest.getQuantityInCart());
    }

}
