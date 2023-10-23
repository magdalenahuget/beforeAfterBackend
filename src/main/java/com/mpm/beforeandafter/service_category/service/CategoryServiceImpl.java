package com.mpm.beforeandafter.service_category.service;

import com.mpm.beforeandafter.service_category.model.Category;
import com.mpm.beforeandafter.service_category.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    /*
       in the future create a DTO class to handle the flow of data between the client and the
       database.
     */


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        log.info("Creating category: {}", category);
        Category savedCategory = categoryRepository.save(category);
        log.info("Category created: {}", savedCategory);
        return savedCategory;
    }

    @Override
    public Set<Category> getCategories() {
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        log.info("Getting category by id: {}", categoryId);
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException(
                        "There is no employee with the given ID: " + categoryId));
    }

    @Override
    public Category updateCategory(Long categoryId, Category updatedCategory) {
        log.info("Updating category with ID: {} with data: {}", categoryId, updatedCategory);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException(
                        "There is no employee with the given ID: " + categoryId));

        category.setCategoryName(updatedCategory.getCategoryName());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.info("Deleting category with id: {}", categoryId);
        categoryRepository
                .findById(categoryId)
                .ifPresentOrElse((categoryRepository::delete),
                        () -> {
                            throw new RuntimeException(
                                    "There is no employee with the given ID: " + categoryId);
                        });
    }
}
