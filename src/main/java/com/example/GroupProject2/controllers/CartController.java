package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.CartItemModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import com.example.GroupProject2.services.CartService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/{userId}")
    public List<CartItemModel> getAllCartItemsByUser(@PathVariable UUID userId){//TODO filter for user in service class
        return cartService.getAllCartItems(userId);
    }

    @PostMapping
    public CartItemModel addInCart(@RequestParam UUID userId, @RequestParam CartItemRequest cartItemRequest){
        return cartService.addInCart(userId,cartItemRequest);
    }

    @PutMapping("/{itemId}")
    public CartItemModel updateCartItem(@PathVariable UUID itemId,@RequestBody CartItemRequest cartItemRequest){
        return cartService.updateCartItem(itemId,cartItemRequest);
    }

    @DeleteMapping("/{ItemId}")
    public void removeItemFromCart(@PathVariable UUID itemId){
        cartService.removeFromCart(itemId);
    }

}
