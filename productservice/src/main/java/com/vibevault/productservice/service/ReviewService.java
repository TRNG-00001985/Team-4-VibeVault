package com.vibevault.productservice.service;

import com.vibevault.productservice.dto.ReviewRequest;
import com.vibevault.productservice.dto.ReviewResponse;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.model.Review;
import com.vibevault.productservice.repository.ProductRepository;
import com.vibevault.productservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    // Create a Review
    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Review review = Review.builder()
                .UserId(reviewRequest.getUserId())
                .product(product)
                .rating((long) reviewRequest.getRating())
                .reviewText(reviewRequest.getReviewText())
                .reviewDate(new Timestamp(System.currentTimeMillis())) // Set current timestamp if not provided
                .build();

        review = reviewRepository.save(review);
        return mapToReviewResponse(review);
    }

    // Get a Review by ID
    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return mapToReviewResponse(review);
    }

    // Update a Review by ID
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingReview.setUserId(reviewRequest.getUserId());
        existingReview.setProduct(product);
        existingReview.setRating((long) reviewRequest.getRating());
        existingReview.setReviewText(reviewRequest.getReviewText());
        existingReview.setReviewDate(new Timestamp(System.currentTimeMillis()));

        Review updatedReview = reviewRepository.save(existingReview);
        return mapToReviewResponse(updatedReview);
    }

    // Delete a Review by ID
    public boolean deleteReviewById(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Get all Reviews
    public List<ReviewResponse> getAllReviewsForProduct(Long productId) {
        List<Review> reviews = reviewRepository.findByProductProductId(productId);  // Correct method
        return reviews.stream()
                .map(this::mapToReviewResponse)
                .collect(Collectors.toList());
    }


    // Map Review entity to ReviewResponse DTO
//    private ReviewResponse mapToReviewResponse(Review review) {
//        return ReviewResponse.builder()
//                .reviewId(Math.toIntExact(review.getReviewId()))
//                .userId(review.getUserId())
//                .productId(review.getProduct().getProductId())
//                .rating(review.getRating().intValue())
//                .reviewText(review.getReviewText())
//                .reviewDate(review.getReviewDate())
//                .build();
//    }
    private ReviewResponse mapToReviewResponse(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getReviewId().intValue())  // Cast Long to int if needed
                .userId(review.getUserId())
                .productId(review.getProduct().getProductId())
                .rating(review.getRating().intValue())
                .reviewText(review.getReviewText())
                .reviewDate(review.getReviewDate())
                .build();
    }

}
