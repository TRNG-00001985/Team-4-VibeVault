package com.revature.revshop.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the cart item

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;  // Reference to the cart

    @Column(nullable = false)
    private int productId; // Unique identifier for the product

    private String name;    // Name of the product
    private BigDecimal price;   // Price of the product
    private int quantity;   // Quantity of the product in the cart
    private String skuCode; // SKU code of the product
}
