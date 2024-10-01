package com.vibevault.productservice.controller;

import com.vibevault.productservice.dto.WishlistRequest;
import com.vibevault.productservice.dto.WishlistResponse;
import com.vibevault.productservice.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WishlistControllerTest {

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    private WishlistRequest wishlistRequest;
    private WishlistResponse wishlistResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        wishlistRequest = new WishlistRequest("1", 1L); // userId = 1, productId = 1
        wishlistResponse = WishlistResponse.builder()
                .wishlistId(1L)
                .userId("1")
                .productId(1L)
                .build();
    }

    @Test
    void testAddToWishlist() {
        when(wishlistService.addToWishlist(wishlistRequest)).thenReturn(wishlistResponse);

        ResponseEntity<WishlistResponse> response = wishlistController.addToWishlist(wishlistRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody().getUserId());
        assertEquals(1L, response.getBody().getProductId());
        verify(wishlistService, times(1)).addToWishlist(wishlistRequest);
    }

    @Test
    void testGetWishlistByUserId() {
        List<WishlistResponse> wishlistResponses = Arrays.asList(wishlistResponse);
        when(wishlistService.getWishlistByUserId("1")).thenReturn(wishlistResponses);

        ResponseEntity<List<WishlistResponse>> response = wishlistController.getWishlistByUserId("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("1", response.getBody().get(0).getUserId());
        verify(wishlistService, times(1)).getWishlistByUserId("1");
    }

    @Test
    void testDeleteWishlistItem() {
        doNothing().when(wishlistService).deleteWishlistItem(1L);

        ResponseEntity<Void> response = wishlistController.deleteWishlistItem(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(wishlistService, times(1)).deleteWishlistItem(1L);
    }

    @Test
    void testClearWishlistForUser() {
        doNothing().when(wishlistService).clearWishlistForUser("1");

        ResponseEntity<Void> response = wishlistController.clearWishlistForUser("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(wishlistService, times(1)).clearWishlistForUser("1");
    }
}
