package com.vibevault.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibevault.productservice.dto.ProductRequest;
import com.vibevault.productservice.dto.ProductResponse;
import com.vibevault.productservice.dto.WishlistResponse;
import com.vibevault.productservice.model.Category;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.model.Wishlist;
import com.vibevault.productservice.repository.CategoryRepository;
import com.vibevault.productservice.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Map ProductRequest to Product Entity
    public Product mapToProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById((long) productRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        return Product.builder()
                .UserId(productRequest.getUserId())
                .category(category)
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .skuCode(productRequest.getSkuCode())
                .discountPrice(productRequest.getDiscountPrice())
                .quantity(productRequest.getQuantity()) // Directly setting quantity as Long
                .imageUrl(productRequest.getImageUrl())
                .build();
    }

    // Map Product Entity to ProductResponse DTO
    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(Math.toIntExact(product.getProductId())) // casting to int as per your DTO
                .userId(product.getUserId())
                .categoryId(Math.toIntExact(product.getCategory().getCategoryId())) // casting to int as per your DTO
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .skuCode(product.getSkuCode())
                .discountPrice(product.getDiscountPrice())
                .quantity(product.getQuantity()) // casting Long to int
                .imageUrl(product.getImageUrl())
                .reviewCount(product.getReviewCount().intValue()) // casting Long to int
                .build();
    }

    // Create a Product
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = mapToProduct(productRequest);

        // Ensure reviewCount is set to a default value (e.g., 0) if not provided
        if (product.getReviewCount() == null) {
            product.setReviewCount(0);
        }

        // Save the product to the repository
        product = productRepository.save(product);
        return mapToProductResponse(product);
    }

    // Get a Product by ID
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return mapToProductResponse(product);
    }

    // Update Product by ID
    public ProductResponse updateProductById(ProductRequest productRequest, Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Update the necessary fields
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setSkuCode(productRequest.getSkuCode());
        existingProduct.setDiscountPrice(productRequest.getDiscountPrice());
        existingProduct.setQuantity (productRequest.getQuantity());  // Directly set the Long quantity
        existingProduct.setImageUrl(productRequest.getImageUrl());

        // Update the category if provided
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById((long) productRequest.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            existingProduct.setCategory(category);
        }

        existingProduct = productRepository.save(existingProduct);
        return mapToProductResponse(existingProduct);
    }

    // Delete Product by ID
    public boolean deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Find products by User ID
//    public List<ProductResponse> findProductByUserId(String userId) {
//        List<Product> products = productRepository.findProductByUserId(userId);
//                return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
//                      
//    }

    // Get All Products
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    // Method to fetch products by category ID
 // Method to fetch products by category ID
    public List<ProductResponse> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryCategoryId(categoryId);
        System.out.println("Products found: " + products.size()); // Logging the number of products
        return products.stream().map(this::mapToProductResponse).toList();
    }


}
