package com.vibevault.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibevault.productservice.dto.CategoryRequest;
import com.vibevault.productservice.dto.CategoryResponse;
import com.vibevault.productservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryResponse categoryResponse;
    private CategoryRequest categoryRequest;

    @BeforeEach
    public void setup() {
        categoryResponse = CategoryResponse.builder()
                .categoryId(1)
                .categoryName("Electronics")
                .description("All Electronics products are available")
                .build();

        categoryRequest = CategoryRequest.builder()
                .categoryName("Electronics")
                .description("All Electronics products are available")
                .build();
    }

    @Test
    public void testCreateCategory() throws Exception {
        Mockito.when(categoryService.createCategory(any(CategoryRequest.class))).thenReturn(categoryResponse);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryName").value(categoryResponse.getCategoryName())) 
                .andExpect(jsonPath("$.description").value(categoryResponse.getDescription()));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(categoryResponse);

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(categoryResponse.getCategoryId())) 
                .andExpect(jsonPath("$.categoryName").value(categoryResponse.getCategoryName()))  
                .andExpect(jsonPath("$.description").value(categoryResponse.getDescription()));
    }

    @Test
    public void testUpdateCategoryById() throws Exception {
        Mockito.when(categoryService.updateCategory(eq(1L), any(CategoryRequest.class))).thenReturn(categoryResponse);

        mockMvc.perform(put("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(categoryResponse.getCategoryId()))  
                .andExpect(jsonPath("$.categoryName").value(categoryResponse.getCategoryName()))  
                .andExpect(jsonPath("$.description").value(categoryResponse.getDescription()));
    }

    @Test
    public void testDeleteCategoryById() throws Exception {
        Mockito.when(categoryService.deleteCategory(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<CategoryResponse> categories = Arrays.asList(categoryResponse);
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId").value(categoryResponse.getCategoryId()))  
                .andExpect(jsonPath("$[0].categoryName").value(categoryResponse.getCategoryName())) 
                .andExpect(jsonPath("$[0].description").value(categoryResponse.getDescription()));
    }
}
