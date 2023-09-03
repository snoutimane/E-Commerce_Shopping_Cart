package com.UserManagement.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.UserManagement.model.CartItem;

@FeignClient(value="CART-SERVICE",url="http://localhost:8081/")
public interface CartService {
	
	@PostMapping("/cart/addToCart")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public String addToCart(@RequestBody CartItem cart);
	
	@DeleteMapping("/cart/removeFromCart/{cartId}")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public void deleteFromCart(@PathVariable int cartId);
	
	@PutMapping("/cart/changeQuantity/{cartId}/{quantity}")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public void changeQuantity(@PathVariable int cartId,@PathVariable int quantity);
	
	@GetMapping("/cart/viewCart")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public List<CartItem> viewCart();

}
