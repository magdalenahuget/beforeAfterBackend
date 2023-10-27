package com.mpm.beforeandafter.category.service;

import com.mpm.beforeandafter.category.dto.CategoryNameRequest;
import com.mpm.beforeandafter.category.dto.CategoryResponse;
import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.repository.CategoryRepository;
import com.mpm.beforeandafter.exception.CategoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_NOT_FOUND_MSG_TEMPLATE =
            "There is no category with the given ID: {}";
    private static final String CATEGORY_NOT_FOUND_MSG = "There is no category with the given ID: ";

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse createCategory(CategoryNameRequest request) {
        log.debug("Creating category: {}", request);
        Category category = new Category();
        category.setName(request.categoryName());
        category = categoryRepository.save(category);
        log.info("Category created: {}", category);
        return CategoryResponse.map(category);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        log.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Getting all categories (count): {}", categories.size());
        return categories.stream()
                .map(CategoryResponse::map)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        log.debug("Getting category by id: {}", categoryId);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_MSG_TEMPLATE, categoryId);
                    return new CategoryNotFoundException(CATEGORY_NOT_FOUND_MSG + categoryId);
                });
        log.info("Successfully fetched category with ID: {}", categoryId);
        return CategoryResponse.map(category);
    }

    @Override
    public CategoryResponse updateCategoryName(Long categoryId, CategoryNameRequest request) {
        log.debug("Updating category with ID: {} with data: {}", categoryId, request);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_MSG_TEMPLATE, categoryId);
                    return new CategoryNotFoundException(
                            CATEGORY_NOT_FOUND_MSG + categoryId);
                });
        category.setName(request.categoryName());
        category = categoryRepository.save(category);
        log.info("Category updated: {}", category.getName());
        return CategoryResponse.map(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.debug("Deleting category with id: {}", categoryId);
        categoryRepository
                .findById(categoryId)
                .ifPresentOrElse(category -> {
                            categoryRepository.delete(category);
                            log.info("Category with id: {} deleted successfully", categoryId);
                        },
                        () -> {
                            log.error(CATEGORY_NOT_FOUND_MSG_TEMPLATE, categoryId);
                            throw new CategoryNotFoundException(
                                    CATEGORY_NOT_FOUND_MSG + categoryId);
                        });
    }
}