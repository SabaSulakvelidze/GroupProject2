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

    private static HashMap<Integer, CartItemModel> cart;
    int index = 0;

    @PostConstruct
    public void init() {
        cart = new HashMap<>();
    }

    public CartItemModel addInCart(Integer userId, UserRole userRole, CartItemRequest cartItemRequest) {
        validateUser(userId, userRole);

        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setId(index++);
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

    public CartItemModel updateCartItem(Integer userId, UserRole userRole, Integer itemId, CartItemRequest newCartItemRequest) {
        validateUser(userId, userRole);
        CartItemModel cartItemModel = cart.get(itemId);
        if (cartItemModel == null) throw new RuntimeException("Item in Cart not found with ID: " + itemId);

        cartItemModel.setProductId(newCartItemRequest.getProductId());
        cartItemModel.setUserId(newCartItemRequest.getUserId());
        cartItemModel.setQuantityInCart(newCartItemRequest.getQuantityInCart());

        return cartItemModel;
    }

    public void removeFromCart(Integer userId, UserRole userRole, Integer itemId) {
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

    public void purchaseSingleProduct(Integer userId, UserRole userRole,Integer productId) {
        validateUser(userId, userRole);

        UserModel singleUser = userService.getSingleUser(userId);
        ProductModel singleProduct = productService.getSingleProduct(productId);
        if (singleProduct.getQuantity() == 0)
            throw new RuntimeException("There are not enough items in stock");
        if (singleUser.getBudget() < singleProduct.getPrice())
            throw new RuntimeException("User does not have enough money");
        singleProduct.setQuantity(singleProduct.getQuantity() - 1);
        singleUser.setBudget(singleUser.getBudget() - singleProduct.getPrice());
    }

    public void purchaseEverythingInCart(Integer userId, UserRole userRole) {
        validateUser(userId, userRole);

        UserModel singleUser = userService.getSingleUser(userId);
        double totalCost = 0.00;
        for (CartItemModel cartItem : getAllCartItems(userId)) {
            ProductModel singleProduct = productService.getSingleProduct(cartItem.getProductId());
            if (singleProduct.getQuantity() == 0)
                throw new RuntimeException("There are not enough items in stock");
            if (singleProduct.getQuantity() < cartItem.getQuantityInCart())
                throw new RuntimeException("Not enough items in stock, product id: " + cartItem.getProductId());
            totalCost += singleProduct.getPrice() * cartItem.getQuantityInCart();
        }

        if (singleUser.getBudget() < totalCost)
            throw new RuntimeException("User does not have enough money");


        singleUser.setBudget(singleUser.getBudget() - totalCost);
        for (CartItemModel cartItem : getAllCartItems(userId)) {
            ProductModel singleProduct = productService.getSingleProduct(cartItem.getProductId());
            singleProduct.setQuantity(singleProduct.getQuantity() - cartItem.getQuantityInCart());
        }

        for (CartItemModel cartItem : getAllCartItems(userId)) {
            cart.remove(cartItem.getId());
        }
    }
}
