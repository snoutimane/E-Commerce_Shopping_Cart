package com.Cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cart.exception.ResourceNotFoundException;
import com.Cart.externalservices.ProductService;

import com.Cart.model.CartItem;
import com.Cart.model.Product;
import com.Cart.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService prodService;

    
    public CartItem addToCart(CartItem cart) {
    	// Retrieve the product details from the ProductService
        Product product = prodService.viewProductById(cart.getProductId());
        // Check if the product exist
        if (product != null) {
            
        	// Create a new cart
            cart.setProductName(product.getProductName());
            cart.setPrice(product.getPrice());
            cart.setQuantity(1); // Assuming default quantity is 1
            cart.setImage(product.getImage());
            cart.setTotalAmount(cart.getTotalAmount()+product.getPrice());
            
            // Save the new cart in the cart repository
            return cartRepository.save(cart);
            //return "Added to cart";
        } else {
            throw new ResourceNotFoundException("Invalid product ID");
        }
    }

    
    public void deleteFromCart(int cartId) {
        CartItem cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cartRepository.deleteById(cartId);
        } else {
            throw new ResourceNotFoundException("Cart item not found for cart ID: " + cartId );
        }
    }

    
    public void changeQuantity(int cartId, int quantity) {
        CartItem cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null ) {
            double unitPrice = cart.getPrice();
            double totalAmount = unitPrice * quantity;

            cart.setQuantity(quantity);
            cart.setTotalAmount(totalAmount);
        } else {
            throw new ResourceNotFoundException("Cart item not found for cart ID: " + cartId );
        }
    }
    
    public List<CartItem> viewCart() {
        return cartRepository.findAll();
    }
}
