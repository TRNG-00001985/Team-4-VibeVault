package com.vibevault.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;
    
    @Column(name = "user_id", nullable = false)
    private String userId; 

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; 
}
