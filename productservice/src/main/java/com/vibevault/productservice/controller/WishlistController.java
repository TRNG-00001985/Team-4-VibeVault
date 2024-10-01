package com.vibevault.productservice.controller;

import com.vibevault.productservice.dto.WishlistRequest;
import com.vibevault.productservice.dto.WishlistResponse;
import com.vibevault.productservice.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Add item to wishlist
    @PostMapping
    public ResponseEntity<WishlistResponse> addToWishlist(@RequestBody WishlistRequest wishlistRequest) {
        WishlistResponse wishlistResponse = wishlistService.addToWishlist(wishlistRequest);
        return new ResponseEntity<>(wishlistResponse, HttpStatus.CREATED);
    }

    // Get all wishlist items for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishlistResponse>> getWishlistByUserId(@PathVariable String userId) {
        List<WishlistResponse> wishlist = wishlistService.getWishlistByUserId(userId);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    // Delete wishlist item by ID
    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlistItem(wishlistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Clear wishlist for a user
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> clearWishlistForUser(@PathVariable String userId) {
        wishlistService.clearWishlistForUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
