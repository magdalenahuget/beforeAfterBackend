package com.mpm.beforeandafter.category.dto;

import com.mpm.beforeandafter.category.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCategoryResponse {
    private Long id;
    private String name;

    public static GetCategoryResponse map(Category category) {
        return GetCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
