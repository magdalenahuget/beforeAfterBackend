package com.mpm.beforeandafter.category.dto;

import com.mpm.beforeandafter.category.model.Category;

public record CategoryResponse(Long id, String categoryName) {
    public static CategoryResponse map(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}

//import com.mpm.beforeandafter.category.model.Category;
//import lombok.Builder;
//import lombok.Data;
//
//
//@Data
//@Builder
//public class CategoryResponse{
//    private Long id;
//    private String categoryName;
//
//    public static CategoryResponse map(Category category) {
//        return CategoryResponse.builder()
//                .id(category.getId()).categoryName(category.getName())
//                .build();
//    }
//}