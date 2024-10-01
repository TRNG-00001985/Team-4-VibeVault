package com.vibevault.productservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WishlistResponse {
    private Long wishlistId;  
    private String userId;      
    private Long productId;   
}
