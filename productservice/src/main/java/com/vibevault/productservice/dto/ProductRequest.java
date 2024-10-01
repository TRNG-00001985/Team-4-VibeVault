package com.vibevault.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String userId;  // Assuming seller ID will be passed from the client
    private Integer categoryId; // Category ID to associate the product
    private String productName;
    private String description;
    private BigDecimal price;
    private String skuCode;  // Unique product identifier (Stock Keeping Unit)
    private BigDecimal discountPrice;
    private int quantity;
    private String imageUrl;
}