package com.vibevault.productservice.service;

import com.vibevault.productservice.dto.WishlistRequest;
import com.vibevault.productservice.dto.WishlistResponse;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.model.Wishlist;
import com.vibevault.productservice.repository.ProductRepository;
import com.vibevault.productservice.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add item to wishlist
    public WishlistResponse addToWishlist(WishlistRequest wishlistRequest) {
        Product product = productRepository.findById(wishlistRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Wishlist wishlist = Wishlist.builder()
                .userId(wishlistRequest.getUserId()) // Use camel case for consistency
                .product(product)
                .build();

        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return mapToWishlistResponse(savedWishlist);
    }

    // Get all wishlist items for a user
    public List<WishlistResponse> getWishlistByUserId(String userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserId(userId);
        return wishlistItems.stream().map(this::mapToWishlistResponse).collect(Collectors.toList());
    }

    // Delete wishlist item by ID
    public void deleteWishlistItem(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    // Clear wishlist for a user
    public void clearWishlistForUser(String userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserId(userId);
        wishlistRepository.deleteAll(wishlistItems);
    }

    private WishlistResponse mapToWishlistResponse(Wishlist wishlist) {
        return WishlistResponse.builder()
                .wishlistId(wishlist.getWishlistId())
                .userId(wishlist.getUserId())
                .productId(wishlist.getProduct().getProductId())
                .build();
    }
}
