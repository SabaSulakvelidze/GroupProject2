package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.CartItemModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import com.example.GroupProject2.services.CartService;
import com.example.GroupProject2.services.ProductService;
import com.example.GroupProject2.services.UserService;
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
    public List<CartItemModel> getAllCartItemsByUser(@PathVariable Integer userId) {
        return cartService.getAllCartItems(userId);
    }

    @PostMapping
    public CartItemModel addInCart(@RequestParam Integer userId, @RequestParam UserRole userRole,
                                   @RequestBody CartItemRequest cartItemRequest) {
        cartService.reduceProductQuantity(cartItemRequest);
        cartService.reduceBudget(cartItemRequest);
        return cartService.addInCart(userId, userRole, cartItemRequest);
    }

    @PutMapping("/{itemId}")
    public CartItemModel updateCartItem(@RequestParam Integer userId, @RequestParam UserRole userRole,
                                        @PathVariable UUID itemId, @RequestBody CartItemRequest cartItemRequest) {
        return cartService.updateCartItem(userId, userRole, itemId, cartItemRequest);
    }

    @DeleteMapping("/{itemId}")
    public void removeItemFromCart(@RequestParam Integer userId, @RequestParam UserRole userRole,
                                   @PathVariable UUID itemId) {
        cartService.increaseBudgetAndQuantity(itemId);
        cartService.removeFromCart(userId, userRole, itemId);
    }

}
