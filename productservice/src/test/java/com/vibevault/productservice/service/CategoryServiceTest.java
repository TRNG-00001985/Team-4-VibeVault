package com.vibevault.productservice.service;

import com.vibevault.productservice.dto.CategoryRequest;
import com.vibevault.productservice.dto.CategoryResponse;
import com.vibevault.productservice.model.Category;
import com.vibevault.productservice.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryRequest categoryRequest;
    private Category category;

    @BeforeEach
    void setUp() {
        categoryRequest = CategoryRequest.builder()
                .categoryName("Electronics")
                .description("All electronic items")
                .build();

        category = Category.builder()
                .categoryId(1L)
                .categoryName("Electronics")
                .description("All electronic items")
                .build();
    }

    @Test
    void createCategory_ShouldReturnCategoryResponse() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);

        assertNotNull(categoryResponse);
        assertEquals("Electronics", categoryResponse.getCategoryName());
        assertEquals("All electronic items", categoryResponse.getDescription());
    }

    @Test
    void getCategoryById_ShouldReturnCategoryResponse() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        CategoryResponse categoryResponse = categoryService.getCategoryById(1L);

        assertNotNull(categoryResponse);
        assertEquals("Electronics", categoryResponse.getCategoryName());
        assertEquals("All electronic items", categoryResponse.getDescription());
    }

    @Test
    void updateCategory_ShouldReturnUpdatedCategoryResponse() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryResponse updatedCategoryResponse = categoryService.updateCategory(1L, categoryRequest);

        assertNotNull(updatedCategoryResponse);
        assertEquals("Electronics", updatedCategoryResponse.getCategoryName());
        assertEquals("All electronic items", updatedCategoryResponse.getDescription());
    }

    @Test
    void deleteCategoryById_ShouldReturnTrue() {
        doNothing().when(categoryRepository).deleteById(1L);

        boolean result = categoryService.deleteCategory(1L);

        assertTrue(result);
        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategoryResponses() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        List<CategoryResponse> categoryResponses = categoryService.getAllCategories();

        assertNotNull(categoryResponses);
        assertEquals(1, categoryResponses.size());
        assertEquals("Electronics", categoryResponses.get(0).getCategoryName());
    }
}
