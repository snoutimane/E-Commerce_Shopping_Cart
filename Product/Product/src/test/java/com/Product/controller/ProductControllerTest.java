package com.Product.controller;

import com.Product.exception.InvalidProductException;
import com.Product.model.Products;
import com.Product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() throws InvalidProductException {
        // Positive Test Case
        // Arrange
        Products product = new Products();
        when(productService.addProduct(any(Products.class))).thenReturn(product);

        // Act
        Products result = productController.addProduct(product);

        // Assert
        verify(productService, times(1)).addProduct(any(Products.class));
        assertEquals(product, result);

        // Negative Test Case
        // Invalid Product
        when(productService.addProduct(any(Products.class))).thenThrow(new InvalidProductException("Invalid product"));

        assertThrows(InvalidProductException.class, () -> {
            productController.addProduct(product);
        });
    }

    @Test
    public void testViewAllProducts() {
        // Positive Test Case
        // Arrange
        List<Products> productList = new ArrayList<>();
        when(productService.viewAllProducts()).thenReturn(productList);

        // Act
        List<Products> result = productController.viewAllProducts();

        // Assert
        verify(productService, times(1)).viewAllProducts();
        assertEquals(productList, result);
    }

    @Test
    public void testViewProductById() throws InvalidProductException {
        // Positive Test Case
        // Arrange
        int productId = 1;
        Products product = new Products();
        Optional<Products> optionalProduct = Optional.of(product);
        when(productService.viewProductById(productId)).thenReturn(optionalProduct);

        // Act
        Optional<Products> result = productController.viewProductById(productId);

        // Assert
        verify(productService, times(1)).viewProductById(productId);
        assertEquals(optionalProduct, result);

        // Negative Test Case
        // Non-existent Product ID
//        int nonExistentProductId = 999;
//        when(productService.viewProductById(nonExistentProductId)).thenReturn(Optional.empty());
//
//        assertThrows(InvalidProductException.class, () -> {
//            productController.viewProductById(nonExistentProductId);
//        });
    }

    @Test
    public void testViewProductsByCategory() throws InvalidProductException {
        // Positive Test Case
        // Arrange
        String category = "Electronics";
        List<Products> productList = new ArrayList<>();
        when(productService.viewProductsByCategory(category)).thenReturn(productList);

        // Act
        List<Products> result = productController.viewProductsByCategory(category);

        // Assert
        verify(productService, times(1)).viewProductsByCategory(category);
        assertEquals(productList, result);
    }

    @Test
    public void testViewProductByName() throws InvalidProductException {
        // Positive Test Case
        // Arrange
        String productName = "Phone";
        List<Products> productList = new ArrayList<>();
        when(productService.viewProductByName(productName)).thenReturn(productList);

        // Act
        List<Products> result = productController.viewProductByName(productName);

        // Assert
        verify(productService, times(1)).viewProductByName(productName);
        assertEquals(productList, result);
    }

    @Test
    public void testDeleteProductById() throws InvalidProductException {
        // Positive Test Case
        // Arrange
        int productId = 1;
        String expectedResult = "Product deleted successfully";
        when(productService.deleteProductById(productId)).thenReturn(expectedResult);

        // Act
        String result = productController.deleteProductById(productId);

        // Assert
        verify(productService, times(1)).deleteProductById(productId);
        assertEquals(expectedResult, result);

        // Negative Test Case
        // Non-existent Product ID
        int nonExistentProductId = 999;
        when(productService.deleteProductById(nonExistentProductId)).thenReturn(null);

        assertThrows(InvalidProductException.class, () -> {
            productController.deleteProductById(nonExistentProductId);
        });
    }

    @Test
    public void testEditProduct() throws InvalidProductException {
        // Positive Test Case
        // Arrange
        int productId = 1;
        Products product = new Products();
        when(productService.editProduct(any(Products.class))).thenReturn(product);

        // Act
        Products result = productController.editProduct(product, productId);

        // Assert
        verify(productService, times(1)).editProduct(any(Products.class));
        assertEquals(product, result);

        // Negative Test Case
        // Invalid Product
        when(productService.editProduct(any(Products.class))).thenThrow(new InvalidProductException("Invalid product"));

        assertThrows(InvalidProductException.class, () -> {
            productController.editProduct(product, productId);
        });
    }
}
