package com.Cart.externalservices;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Cart.model.Product;





@FeignClient(value="PRODUCT-SERVICE",url="http://localhost:8082/")
public interface ProductService {
	
	@GetMapping("/products/viewProductById/{productId}")
	public Product viewProductById(@PathVariable int productId);

	

}
