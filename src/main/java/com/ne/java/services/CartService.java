package com.ne.java.services;

import com.ne.java.models.Cart;
import com.ne.java.models.CartItem;
import com.ne.java.models.Product;
import com.ne.java.models.Purchase;
import com.ne.java.repositories.ProductRepository;
import com.ne.java.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    private final Map<Long, CartItem> cartItems = new HashMap<>();
    private final PurchaseRepository purchaseRepository;
//product repository
    private final ProductRepository productRepository;
    public CartService(PurchaseRepository purchaseRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    public void addItemToCart(Long productId, int quantity) {
        // Check if the item is already in the cart
        if (cartItems.containsKey(productId)) {
            CartItem existingItem = cartItems.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setTotalPrice(existingItem.getTotalPrice() + (existingItem.getQuantity() * getProductPrice(productId)));
        } else {
            CartItem newItem = new CartItem(productId, quantity, quantity * getProductPrice(productId));
            cartItems.put(productId, newItem);
        }
    }

    public Cart getCart() {
        List<CartItem> cartItemList = new ArrayList<>(cartItems.values());
        Cart cart = new Cart();
        cart.setItems(cartItemList);
        return cart;
    }

    private double getProductPrice(Long productId) {
        // Fetch the product price from the database or other data source
        // Implement your logic here
        return 0.0; // Replace with the actual product price
    }
    public void checkout() {
        List<Purchase> purchases = new ArrayList<>();
        for (CartItem cartItem : cartItems.values()) {
            Product product = productRepository.findById(cartItem.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + cartItem.getProductId()));

            Purchase purchased = new Purchase();
            purchased.setQuantity(cartItem.getQuantity());
            purchased.setTotal(cartItem.getTotalPrice());
            purchased.setProduct(product);
            purchased.setDate(new Date());

            purchases.add(purchased);
        }

        purchaseRepository.saveAll(purchases);

        cartItems.clear();
    }
}

