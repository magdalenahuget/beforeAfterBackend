package com.mpm.beforeandafter.category.dto;

import com.mpm.beforeandafter.category.model.Category;

public record CategoryResponse(Long id, String categoryName) {
    public static CategoryResponse map(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}