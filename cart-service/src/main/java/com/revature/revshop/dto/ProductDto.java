package com.revature.revshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
//    private Long id;
//    private String name;
//    private String skuCode;
//    private Double price;
    // Add other relevant fields as necessary
	private int productId;
    private String userId; // Assuming you'll include the seller's ID
    private Integer categoryId; // The associated category ID
    private String productName;
    private String description;
    private BigDecimal price;
    private String skuCode; // Unique product identifier
    private BigDecimal discountPrice;
    private int quantity;
    private String imageUrl;
    private int reviewCount;
}
