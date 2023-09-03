package com.UserManagement.externalservice;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.UserManagement.model.Products;


@FeignClient(value="PRODUCT-SERVICE",url="http://localhost:8082/")
public interface ProductService {
	
	@PostMapping("/addProduct")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DEALER')")
	public Products addProduct(@RequestBody Products prod);
	
	@GetMapping("/viewAllProducts")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public List<Products> viewAllProducts();
	
	@GetMapping("/viewProductById/{productId}")
	public Optional<Products> viewProductById(@PathVariable int productId);
	
	@GetMapping("/viewProductsByCategory/{category}")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public List<Products> viewProductsByCategory(@PathVariable String category);
	
	@GetMapping("/viewProductByName/{productName}")
	@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
	public List<Products> viewProductByName(@PathVariable String productName);
	
	@DeleteMapping("/deleteProduct/{productId}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DEALER')")
	public String deleteProductById(@PathVariable int productId);
	
	@PutMapping("/editProduct/{productId}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DEALER')")
	public Products editProduct(@RequestBody Products prod,@PathVariable int productId);
}
