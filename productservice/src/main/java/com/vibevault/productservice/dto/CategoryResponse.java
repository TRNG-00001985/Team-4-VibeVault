package com.vibevault.productservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private int categoryId;       // Unique identifier of the category
    private String categoryName;  // Name of the category
    private String description;   // Description of the category
}
