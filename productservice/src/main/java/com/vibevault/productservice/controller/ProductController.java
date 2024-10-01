package com.vibevault.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vibevault.productservice.dto.ProductRequest;
import com.vibevault.productservice.dto.ProductResponse;
import com.vibevault.productservice.dto.WishlistResponse;
import com.vibevault.productservice.model.Category;
import com.vibevault.productservice.service.ProductService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")  // Removed trailing "/"
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    // Get a product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    
    //Get a product by user ID
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<ProductResponse>> findProductByUserId(@PathVariable String userId) {
//        List<ProductResponse> products = productService.findProductByUserId(userId);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    // Update a product by its ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        ProductResponse productResponse = productService.updateProductById(productRequest, id);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProductById(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductResponse> products = productService.getProductsByCategoryId(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no products found
        }
        return ResponseEntity.ok(products);
    }   
}
