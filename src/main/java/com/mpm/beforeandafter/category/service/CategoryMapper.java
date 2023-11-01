package com.mpm.beforeandafter.category.service;

import com.mpm.beforeandafter.category.dto.CategoryNameRequestDTO;
import com.mpm.beforeandafter.category.dto.CategoryResponseDTO;
import com.mpm.beforeandafter.category.model.Category;
import org.springframework.stereotype.Component;

@Component
class CategoryMapper {
    Category mapToEntity(CategoryNameRequestDTO requestDTO) {
        return Category.builder()
                .name(requestDTO.categoryName())
                .build();
    }

    CategoryResponseDTO mapToResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .categoryName(category.getName())
                .build();
    }
}
