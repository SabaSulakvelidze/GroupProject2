package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.CartItemModel;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    UserServices userServices;

    private static HashMap<UUID, CartItemModel> cart;

    @PostConstruct
    public void init() {
        cart = new HashMap<>();
    }

    public CartItemModel addInCart(CartItemRequest cartItemRequest) {
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setId(UUID.randomUUID());
        cartItemModel.setProductId(cartItemRequest.getProductId());
        cartItemModel.setUserId(cartItemRequest.getUserId());
        cartItemModel.setQuantityInCart(cartItemRequest.getQuantityInCart());
        cart.put(cartItemModel.getId(), cartItemModel);
        return cartItemModel;
    }

    public List<CartItemModel> getAllCartItems(UUID userId) {
        List<CartItemModel> cartItemsByUser = new ArrayList<>(cart.values());
        cartItemsByUser.removeIf(item -> !item.getUserId().toString().equals(userId.toString()));
        return cartItemsByUser;
    }

    public CartItemModel updateCartItem(UUID itemId, CartItemRequest newCartItemRequest) {
        CartItemModel cartItemModel = cart.get(itemId);
        if (cartItemModel == null) throw new RuntimeException("Item in Cart not found with ID: " + itemId);
        cartItemModel.setProductId(newCartItemRequest.getProductId());
        cartItemModel.setUserId(newCartItemRequest.getUserId());
        cartItemModel.setQuantityInCart(newCartItemRequest.getQuantityInCart());
        return cartItemModel;
    }

    public void removeFromCart(UUID itemId) {
        cart.remove(itemId);
    }

    public void validateUser(Integer userId, UserRole userRole) {
        UserModel user = userServices.getSingleUser(userId);

        if (user == null) throw new RuntimeException("User not found with ID: " + userId);

        if (!user.getUserRole().equals(userRole)) throw new RuntimeException("User role is incorrect");

        if (!user.getUserRole().equals(UserRole.ADMIN)) throw new RuntimeException("User does not have permission, not admin");
    }

}
