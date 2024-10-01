package com.vibevault.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequest {
    
    private String userId;       // Buyer ID
    private Long productId;     // Product ID
    private int rating;         // Rating for the product
    private String reviewText;  // Review text

    // Optional if the review date needs to be set manually (otherwise set automatically in the service layer)
//    private Timestamp reviewDate; 
}
