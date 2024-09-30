package com.example.GroupProject2.models.entity;

import java.util.UUID;

public class CartItemModel {

    private UUID id;
    private Integer userId;
    private UUID productId;
    private Integer quantityInCart;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(Integer quantityInCart) {
        this.quantityInCart = quantityInCart;
    }
}
