package com.mpm.beforeandafter.category.service;

import com.mpm.beforeandafter.category.dto.CategoryNameRequestDTO;
import com.mpm.beforeandafter.category.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryNameRequestDTO request);

    List<CategoryResponseDTO> getCategories();

    CategoryResponseDTO getCategoryById(Long categoryId);

    CategoryResponseDTO updateCategoryName(Long categoryId, CategoryNameRequestDTO request);

    void deleteCategory(Long categoryId);
}