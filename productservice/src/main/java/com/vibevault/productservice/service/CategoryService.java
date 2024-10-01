package com.vibevault.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibevault.productservice.dto.CategoryRequest;
import com.vibevault.productservice.dto.CategoryResponse;
import com.vibevault.productservice.model.Category;
import com.vibevault.productservice.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Create a new category
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = mapToCategory(categoryRequest);
        category = categoryRepository.save(category);
        return mapToCategoryResponse(category);
    }

    // Get category by ID
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return mapToCategoryResponse(category);
    }

    // Get all categories
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    // Update category by ID
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        category = categoryRepository.save(category);

        return mapToCategoryResponse(category);
    }

    // Delete category by ID
    public boolean deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to map CategoryRequest to Category entity
    private Category mapToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .build();
    }

    // Utility method to map Category entity to CategoryResponse
    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(Math.toIntExact(category.getCategoryId()))
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
    }
}
