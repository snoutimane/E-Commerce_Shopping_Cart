package com.Cart.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cart.model.CartItem;
import com.Cart.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/addToCart")
	@CrossOrigin(origins = "http://localhost:4200")
	public CartItem addToCart(@RequestBody CartItem cart) {
		return cartService.addToCart(cart);
	}
	@DeleteMapping("/removeFromCart/{cartId}/{productId}")
	public void deleteFromCart(@PathVariable int cartId) {
		cartService.deleteFromCart(cartId);
	}
	
	@PutMapping("/changeQuantity/{cartId}/{quantity}")
	public void changeQuantity(@PathVariable int cartId,@PathVariable int quantity) {
		cartService.changeQuantity(cartId,quantity);
	}
	
	@GetMapping("/viewCart")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<CartItem> viewCart(){
		return cartService.viewCart();
	}

}
