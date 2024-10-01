package com.vibevault.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    
    private int reviewId;        // Review ID
    private String userId;         // Buyer ID
    private Long productId;      // Product ID
    private int rating;          // Rating for the product
    private String reviewText;   // Review text
    private Timestamp reviewDate; // Date the review was posted
}
