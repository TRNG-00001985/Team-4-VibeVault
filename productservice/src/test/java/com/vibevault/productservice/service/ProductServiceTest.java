package com.vibevault.productservice.service;

import com.vibevault.productservice.dto.ProductRequest;
import com.vibevault.productservice.dto.ProductResponse;
import com.vibevault.productservice.model.Category;
import com.vibevault.productservice.model.Product;
import com.vibevault.productservice.repository.CategoryRepository;
import com.vibevault.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Category category;
    private ProductRequest productRequest;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category(1L, "Electronics","All are Electronics are avaialable ");
        productRequest = new ProductRequest("1", 1, "Product1", "Description", 
                                             BigDecimal.valueOf(100.0), "SKU123", 
                                             BigDecimal.valueOf(90.0), 10, "imageUrl");

        product = Product.builder()
                .productId(1L)
                .UserId("1") // Changed to userId
                .category(category)
                .productName("Product1")
                .description("Description")
                .price(BigDecimal.valueOf(100.0))
                .skuCode("SKU123")
                .discountPrice(BigDecimal.valueOf(90.0))
                .quantity(10) // Adjusted to Long
                .imageUrl("imageUrl")
                .reviewCount(0) // Adjusted to Long
                .build();
    }

    @Test
    public void createProduct_ShouldReturnProductResponse() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.createProduct(productRequest);

        assertNotNull(response);
        assertEquals("Product1", response.getProductName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void getProductById_ShouldReturnProductResponse() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse response = productService.getProductById(1L);

        assertNotNull(response);
        assertEquals("Product1", response.getProductName());
    }

    @Test
    public void updateProductById_ShouldReturnUpdatedProductResponse() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.updateProductById(productRequest, 1L);

        assertNotNull(response);
        assertEquals("Product1", response.getProductName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void deleteProductById_ShouldReturnTrue() {
        doNothing().when(productRepository).deleteById(1L);

        boolean result = productService.deleteProductById(1L);

        assertTrue(result);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllProducts_ShouldReturnListOfProductResponses() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponse> response = productService.getAllProducts();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Product1", response.get(0).getProductName());
    }
}
