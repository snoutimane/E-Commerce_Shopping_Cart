package com.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Product.dao.ProductDao;
import com.Product.exception.InvalidProductException;
import com.Product.externalservices.CartService;
import com.Product.model.CartItemDTO;
import com.Product.model.Products;

@Service
public class ProductService {
	
	@Autowired
	ProductDao dao;
	
	@Autowired
	CartService cartService; // Inject the CartService Feign client
	
	public Products addProduct(Products prod) throws InvalidProductException {
		return dao.addProduct(prod);
	}
	
	public List<Products> viewAllProducts(){
		return dao.viewAllProducts();
	}
	
	public Optional<Products> viewProductById(int productId) throws InvalidProductException{
		return dao.viewProductById(productId);
	}
	
	public List<Products> viewProductsByCategory(String category) throws InvalidProductException{
		return dao.viewProductsByCategory(category);
	}
	
	public List<Products> viewProductByName(String productName) throws InvalidProductException{
		return dao.viewProductByName(productName);
	}
	
	public String deleteProductById(int productId) throws InvalidProductException{
		return dao.deleteProductById(productId);
	}
	
	public Products editProduct(Products prod) throws InvalidProductException{
		return dao.editProduct(prod);
	}
	
	// Method to add a product to the cart
	public String addToCart(int productId) throws InvalidProductException {
	    // Retrieve the product from the Product microservice
	    Optional<Products> product = dao.viewProductById(productId);

	    // Check if the product exists
	    if (product.isPresent()) {
	        // Create a CartItemDTO object and set the product details
	        CartItemDTO cartItem = new CartItemDTO();
	        cartItem.setProductId(product.get().getProductId());
	        cartItem.setQuantity(1); // Set the quantity as needed

	        // Add the cart item to the cart in the Cart microservice using the Feign client
	        return cartService.addToCart(cartItem);
	    } else {
	        // Product not found
	        throw new InvalidProductException("Invalid product ID: " + productId);
	    }
	}
	
}
