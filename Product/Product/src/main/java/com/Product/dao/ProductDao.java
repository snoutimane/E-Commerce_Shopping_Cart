package com.Product.dao;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Product.exception.InvalidProductException;
import com.Product.model.Products;
import com.Product.repository.ProductRepository;

@Component
public class ProductDao {
    @Autowired
    ProductRepository prodRepo;

    public Products addProduct(Products prod) throws InvalidProductException {
        try {
            return prodRepo.save(prod);
        } catch (Exception e) {
            throw new InvalidProductException("Failed to add product: " + e.getMessage());
        }
    }

    public List<Products> viewAllProducts() {
        return prodRepo.findAll();
    }

    public Optional<Products> viewProductById(int productId) throws InvalidProductException {
        try {
            return prodRepo.findById(productId);
        } catch (Exception e) {
            throw new InvalidProductException("Failed to retrieve product by ID: " + e.getMessage());
        }
    }

    public List<Products> viewProductsByCategory(String category) throws InvalidProductException {
        try {
            return prodRepo.findAllByCategory(category);
        } catch (Exception e) {
            throw new InvalidProductException("Failed to retrieve products by category: " + e.getMessage());
        }
    }

    public List<Products> viewProductByName(String productName) throws InvalidProductException {
        try {
            return prodRepo.findByProductName(productName);
        } catch (Exception e) {
            throw new InvalidProductException("Failed to retrieve products by name: " + e.getMessage());
        }
    }

    public String deleteProductById(int productId) throws InvalidProductException {
        try {
            prodRepo.deleteById(productId);
            return "Deleted product successfully";
        } catch (Exception e) {
            throw new InvalidProductException("Failed to delete product: " + e.getMessage());
        }
    }

    public Products editProduct(Products prod) throws InvalidProductException {
        try {
            return prodRepo.save(prod);
        } catch (Exception e) {
            throw new InvalidProductException("Failed to edit product: " + e.getMessage());
        }
    }
}
