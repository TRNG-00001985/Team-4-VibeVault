package com.vibevault.productservice.controller;

import com.vibevault.productservice.dto.ReviewRequest;
import com.vibevault.productservice.dto.ReviewResponse;
import com.vibevault.productservice.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private ReviewRequest reviewRequest;
    private ReviewResponse reviewResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewRequest = new ReviewRequest("1", 1L, 5, "Great product!");
        reviewResponse = ReviewResponse.builder()
                .reviewId(1)
                .userId("1")
                .productId(1L)
                .rating(5)
                .reviewText("Great product!")
                .build();
    }

    @Test
    void testCreateReview() {
        when(reviewService.createReview(any(ReviewRequest.class))).thenReturn(reviewResponse);

        ResponseEntity<ReviewResponse> responseEntity = reviewController.createReview(reviewRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(reviewResponse, responseEntity.getBody());
        verify(reviewService, times(1)).createReview(any(ReviewRequest.class));
    }

    @Test
    void testGetReviewById() {
        when(reviewService.getReviewById(1L)).thenReturn(reviewResponse);

        ResponseEntity<ReviewResponse> responseEntity = reviewController.getReviewById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviewResponse, responseEntity.getBody());
        verify(reviewService, times(1)).getReviewById(1L);
    }

    @Test
    void testUpdateReview() {
        when(reviewService.updateReview(eq(1L), any(ReviewRequest.class))).thenReturn(reviewResponse);

        ResponseEntity<ReviewResponse> responseEntity = reviewController.updateReview(1L, reviewRequest);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(reviewResponse, responseEntity.getBody());
        verify(reviewService, times(1)).updateReview(eq(1L), any(ReviewRequest.class));
    }

    @Test
    void testDeleteReviewById() {
        when(reviewService.deleteReviewById(1L)).thenReturn(true);

        ResponseEntity<Void> responseEntity = reviewController.deleteReviewById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(reviewService, times(1)).deleteReviewById(1L);
    }

    @Test
    void testDeleteReviewById_NotFound() {
        when(reviewService.deleteReviewById(1L)).thenReturn(false);

        ResponseEntity<Void> responseEntity = reviewController.deleteReviewById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(reviewService, times(1)).deleteReviewById(1L);
    }

    @Test
    void testGetAllReviewsForProduct() {
        List<ReviewResponse> reviews = Arrays.asList(reviewResponse);
        when(reviewService.getAllReviewsForProduct(1L)).thenReturn(reviews);

        ResponseEntity<List<ReviewResponse>> responseEntity = reviewController.getAllReviewsForProduct(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviews, responseEntity.getBody());
        verify(reviewService, times(1)).getAllReviewsForProduct(1L);
    }
}
