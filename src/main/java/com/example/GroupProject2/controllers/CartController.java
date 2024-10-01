package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.CartItemModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import com.example.GroupProject2.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return cartService.addInCart(userId, userRole, cartItemRequest);
    }

    @PutMapping("/{itemId}")
    public CartItemModel updateCartItem(@RequestParam Integer userId, @RequestParam UserRole userRole,
                                        @PathVariable Integer itemId, @RequestBody CartItemRequest cartItemRequest) {
        return cartService.updateCartItem(userId, userRole, itemId, cartItemRequest);
    }

    @DeleteMapping("/{itemId}")
    public void removeItemFromCart(@RequestParam Integer userId, @RequestParam UserRole userRole,
                                   @PathVariable Integer itemId) {
        cartService.removeFromCart(userId, userRole, itemId);
    }

    @PostMapping("/purchaseSingle")
    public void purchaseSingleItem(@RequestParam Integer userId, @RequestParam UserRole userRole,
                                   @RequestParam Integer productId) {
        cartService.purchaseSingleProduct(userId, userRole, productId);
    }

    @PostMapping("/purchaseAll")
    public void purchaseEverythingInCart(@RequestParam Integer userId, @RequestParam UserRole userRole) {
        cartService.purchaseEverythingInCart(userId, userRole);
    }

}
