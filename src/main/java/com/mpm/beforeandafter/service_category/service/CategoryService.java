package com.mpm.beforeandafter.service_category.service;

import com.mpm.beforeandafter.service_category.model.Category;

import java.util.Set;

public interface CategoryService {

    /*
       in the future create a DTO class to handle the flow of data between the client and the
       database.
     */

    Category createCategory(Category category);

     Set<Category> getCategories();

    Category getCategoryById(Long categoryId);

    Category updateCategory(Long categoryId, Category category);

    void deleteCategory(Long categoryId);
}
