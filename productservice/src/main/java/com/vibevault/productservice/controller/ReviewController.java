package com.vibevault.productservice.controller;

import com.vibevault.productservice.dto.ReviewRequest;
import com.vibevault.productservice.dto.ReviewResponse;
import com.vibevault.productservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create a review
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.createReview(reviewRequest);
        return new ResponseEntity<>(reviewResponse, HttpStatus.CREATED);
    }

    // Get a review by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        ReviewResponse reviewResponse = reviewService.getReviewById(id);
        return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
    }

    // Update a review by ID
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long id, @RequestBody ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.updateReview(id, reviewRequest);
        return new ResponseEntity<>(reviewResponse, HttpStatus.ACCEPTED);
    }

    // Delete a review by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long id) {
        boolean deleted = reviewService.deleteReviewById(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Get all reviews for a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponse>> getAllReviewsForProduct(@PathVariable Long productId) {
        List<ReviewResponse> reviews = reviewService.getAllReviewsForProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

}
