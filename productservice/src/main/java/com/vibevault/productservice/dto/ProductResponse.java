package com.vibevault.productservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
public class ProductResponse {
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
