package com.Product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Product.exception.InvalidProductException;
import com.Product.model.Products;
import com.Product.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	
	@PostMapping("/addProduct")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'DEALER')")
	public Products addProduct(@RequestBody Products prod) throws InvalidProductException {
		return service.addProduct(prod);
	}
	
	@GetMapping("/viewAllProducts")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MERCHANT')")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Products> viewAllProducts(){
		return service.viewAllProducts();
		
	}
	
	@GetMapping("/viewProductById/{productId}")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MERCHANT')")
	public Optional<Products> viewProductById(@PathVariable int productId) throws InvalidProductException{
		return service.viewProductById(productId);
	}
	
	@GetMapping("/viewProductsByCategory/{category}")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MERCHANT')")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Products> viewProductsByCategory(@PathVariable String category) throws InvalidProductException{
		return service.viewProductsByCategory(category);
	}
	
	@GetMapping("/viewProductByName/{productName}")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MERCHANT')")
	public List<Products> viewProductByName(@PathVariable String productName) throws InvalidProductException{
		return service.viewProductByName(productName);
		
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MERCHANT')")
	public String deleteProductById(@PathVariable int productId) throws InvalidProductException{
		return service.deleteProductById(productId);
		
	}
	@PutMapping("/editProduct/{productId}")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'MERCHANT')")
	public Products editProduct(@RequestBody Products prod,@PathVariable int productId) throws InvalidProductException{
		return service.editProduct(prod);
	}
}
