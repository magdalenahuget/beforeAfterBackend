package com.mpm.beforeandafter.category.service;

import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        log.debug("Creating category: {}", category);
        Category savedCategory = categoryRepository.save(category);
        log.info("Category created: {}", savedCategory);
        return savedCategory;
    }

    @Override
    public List<Category> getCategories() {
        log.debug("Getting all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        log.debug("Getting category by id: {}", categoryId);
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error("There is no category with the given ID: {}", categoryId);
                    return new RuntimeException(
                            "There is no category with the given ID: " + categoryId);
                });
    }

    @Override
    public Category updateCategoryName(Long categoryId, String categoryName) {
        log.debug("Updating category with ID: {} with data: {}", categoryId, categoryName);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error("There is no category with the given ID: {}", categoryId);
                    return new RuntimeException(
                            "There is no category with the given ID: " + categoryId);
                });

        category.setCategoryName(categoryName);

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.debug("Deleting category with id: {}", categoryId);
        categoryRepository
                .findById(categoryId)
                .ifPresentOrElse((categoryRepository::delete),
                        () -> {
                            log.error("There is no category with the given ID: {}", categoryId);
                            throw new RuntimeException(
                                    "There is no category with the given ID: " + categoryId);
                        });
    }
}
