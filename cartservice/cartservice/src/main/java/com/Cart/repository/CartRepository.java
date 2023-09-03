package com.Cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cart.model.CartItem;



@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer>{

	CartItem findByProductName(String productName);

	
}
