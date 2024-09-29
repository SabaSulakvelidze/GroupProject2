package com.example.GroupProject2.services;

import com.example.GroupProject2.models.entity.CartItemModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private static HashMap<UUID, CartItemModel> cart;

    @PostConstruct
    public void init(){
        cart = new HashMap<>();
    }

    public CartItemModel addInCart(UUID userId, CartItemRequest cartItemRequest){
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setId(userId);
        cartItemModel.setUser(cartItemRequest.getUser());
        cartItemModel.setProduct(cartItemRequest.getProduct());
        cartItemModel.setQuantityInCart(cartItemRequest.getQuantityInCart());
        return cartItemModel;
    }

    public List<CartItemModel> getAllCartItems(UUID userId){//TODO filter by user
        return new ArrayList<>(cart.values());
    }

    public CartItemModel updateCartItem(UUID itemId,CartItemRequest cartItemRequest){//TODO
        return new CartItemModel();
    }

    public void removeFromCart(UUID itemId){
        cart.remove(itemId);
    }

}
