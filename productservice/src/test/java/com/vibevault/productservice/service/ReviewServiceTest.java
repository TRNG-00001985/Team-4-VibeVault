package com.vibevault.productservice.service;

import com.vibevault.productservice.dto.ReviewRequest;
import com.vibevault.productservice.dto.ReviewResponse;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.model.Review;
import com.vibevault.productservice.repository.ProductRepository;
import com.vibevault.productservice.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private ReviewRequest reviewRequest;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample product
        product = Product.builder()
                .productId(1L)
                .productName("Product1")
                .description("Description")
                .price(BigDecimal.valueOf(100.0))
                .skuCode("SKU123")
                .build();

        // Sample review
        review = Review.builder()
                .reviewId(1L)
                .UserId("1")
                .product(product)
                .rating(5L)
                .reviewText("Great product!")
                .reviewDate(new Timestamp(System.currentTimeMillis()))
                .build();

        // Sample review request
        reviewRequest = ReviewRequest.builder()
                .userId("1")
                .productId(1L)
                .rating(5)
                .reviewText("Great product!")
                .build();
    }

    @Test
    void createReview() {
        when(productRepository.findById(reviewRequest.getProductId())).thenReturn(Optional.of(product));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponse response = reviewService.createReview(reviewRequest);

        assertNotNull(response);
        assertEquals(review.getReviewText(), response.getReviewText());
        assertEquals(review.getRating().intValue(), response.getRating());

        verify(productRepository, times(1)).findById(reviewRequest.getProductId());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void getReviewById() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        ReviewResponse response = reviewService.getReviewById(1L);

        assertNotNull(response);
        assertEquals(review.getReviewText(), response.getReviewText());

        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void updateReview() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(productRepository.findById(reviewRequest.getProductId())).thenReturn(Optional.of(product));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponse response = reviewService.updateReview(1L, reviewRequest);

        assertNotNull(response);
        assertEquals(review.getReviewText(), response.getReviewText());
        assertEquals(review.getRating().intValue(), response.getRating());

        verify(reviewRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(reviewRequest.getProductId());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void deleteReviewById() {
        when(reviewRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = reviewService.deleteReviewById(1L);

        assertTrue(isDeleted);
        verify(reviewRepository, times(1)).existsById(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllReviewsForProduct() {
        when(reviewRepository.findByProductProductId(1L)).thenReturn(Collections.singletonList(review));

        var reviews = reviewService.getAllReviewsForProduct(1L);

        assertFalse(reviews.isEmpty());
        assertEquals(review.getReviewText(), reviews.get(0).getReviewText());

        verify(reviewRepository, times(1)).findByProductProductId(1L);
    }
}
