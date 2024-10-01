package com.vibevault.productservice.service;

import com.vibevault.productservice.dto.WishlistRequest;
import com.vibevault.productservice.dto.WishlistResponse;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.model.Wishlist;
import com.vibevault.productservice.repository.ProductRepository;
import com.vibevault.productservice.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private WishlistService wishlistService;

    private WishlistRequest wishlistRequest;
    private Wishlist wishlist;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = Product.builder()
                .productId(1L)
                .productName("Test Product")
                .price(BigDecimal.valueOf(100.0))
                .build();

        wishlistRequest = new WishlistRequest("1", 1L); // userId = 1, productId = 1

        wishlist = Wishlist.builder()
                .wishlistId(1L)
                .userId("1")
                .product(product)
                .build();
    }

    @Test
    void testAddToWishlist() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        WishlistResponse response = wishlistService.addToWishlist(wishlistRequest);

        assertEquals("1", response.getUserId());
        assertEquals(1L, response.getProductId());
        verify(productRepository, times(1)).findById(1L);
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testAddToWishlist_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            wishlistService.addToWishlist(wishlistRequest);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(wishlistRepository, never()).save(any(Wishlist.class));
    }

    @Test
    void testGetWishlistByUserId() {
        List<Wishlist> wishlistItems = Arrays.asList(wishlist);
        when(wishlistRepository.findByUserId("1")).thenReturn(wishlistItems);

        List<WishlistResponse> responses = wishlistService.getWishlistByUserId("1");

        assertEquals(1, responses.size());
        assertEquals("1", responses.get(0).getUserId());
        verify(wishlistRepository, times(1)).findByUserId("1");
    }

    @Test
    void testDeleteWishlistItem() {
        doNothing().when(wishlistRepository).deleteById(1L);

        wishlistService.deleteWishlistItem(1L);

        verify(wishlistRepository, times(1)).deleteById(1L);
    }

    @Test
    void testClearWishlistForUser() {
        List<Wishlist> wishlistItems = Arrays.asList(wishlist);
        when(wishlistRepository.findByUserId("1")).thenReturn(wishlistItems);
        doNothing().when(wishlistRepository).deleteAll(wishlistItems);

        wishlistService.clearWishlistForUser("1");

        verify(wishlistRepository, times(1)).findByUserId("1");
        verify(wishlistRepository, times(1)).deleteAll(wishlistItems);
    }
}