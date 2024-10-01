package com.vibevault.productservice.controller;

import com.vibevault.productservice.dto.ProductRequest;
import com.vibevault.productservice.dto.ProductResponse;
import com.vibevault.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        productRequest = new ProductRequest("1", 1, "Product1", "Description",
                BigDecimal.valueOf(100.0), "SKU123", BigDecimal.valueOf(90.0), 10, "imageUrl");

        productResponse = new ProductResponse(1, "1", 1, "Product1", "Description",
                BigDecimal.valueOf(100.0), "SKU123", BigDecimal.valueOf(90.0), 10, "imageUrl", 0);
    }

    @Test
    void createProduct() {
        when(productService.createProduct(productRequest)).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.createProduct(productRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(productResponse.getProductId(), responseEntity.getBody().getProductId());
        verify(productService, times(1)).createProduct(productRequest);
    }

    @Test
    void getProductById() {
        when(productService.getProductById(1L)).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(productResponse.getProductId(), responseEntity.getBody().getProductId());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void updateProductById() {
        when(productService.updateProductById(productRequest, 1L)).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.updateProductById(productRequest,1L);

//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(productResponse.getProductId(), responseEntity.getBody().getProductId());
        verify(productService, times(1)).updateProductById(productRequest, 1L);
    }

    @Test
    void deleteProductById() {
        when(productService.deleteProductById(1L)).thenReturn(true);

        ResponseEntity<Void> responseEntity = productController.deleteProductById(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(productService, times(1)).deleteProductById(1L);
    }

    @Test
    void getAllProducts() {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(productResponse));

        ResponseEntity<List<ProductResponse>> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }
}
