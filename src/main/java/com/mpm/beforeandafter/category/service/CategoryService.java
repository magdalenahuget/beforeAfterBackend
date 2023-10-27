package com.mpm.beforeandafter.category.service;

import com.mpm.beforeandafter.category.dto.CategoryNameRequest;
import com.mpm.beforeandafter.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryNameRequest request);

    List<CategoryResponse> getCategories();

    CategoryResponse getCategoryById(Long categoryId);

    CategoryResponse updateCategoryName(Long categoryId, CategoryNameRequest request);

    void deleteCategory(Long categoryId);
}