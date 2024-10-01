package com.vibevault.productservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WishlistRequest {
    private String userId;   
    private Long productId;
}
