package com.Product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Product.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer>{
	Optional<Products> findById(int productId);
	String deleteById(int productId);
	List<Products> findByProductName(String productName);
	List<Products> findAllByCategory(String category);
}
