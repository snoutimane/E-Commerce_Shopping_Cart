package com.Product.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Product.model.CartItemDTO;



@FeignClient(name = "CART-SERVICE") // Specify the name of the Cart microservice
public interface CartService {
	
	@PostMapping("/cart/addToCart")
	public String addToCart(@RequestBody CartItemDTO cartItem);

}
