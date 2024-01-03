package com.mpm.beforeandafter.category.service;

import com.mpm.beforeandafter.category.dto.CategoryNameRequestDto;
import com.mpm.beforeandafter.category.dto.CategoryResponseDto;
import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.repository.CategoryRepository;
import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_NOT_FOUND_LOG_ERROR_MSG =
            "There is no category with the given ID: {}";
    private static final String CATEGORY_NOT_FOUND_EXCEPTION_MSG =
            "There is no category with the given ID: ";

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryNameRequestDto request) {
        log.info("[REQUEST] Creating new category from request: {}", request);

        log.info("Preparing category to store...");
        Category category = categoryMapper.mapToCategoryEntity(request);
        log.info("Category preparation completed successfully. Category: {}", category);

        log.info("Saving category in database...");
        category = categoryRepository.save(category);
        log.info("Category saved in database: {}", category);

        log.info("Returning image dto...");
        return categoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        log.info("[REQUEST] Fetching all categories...");
        List<Category> categories = categoryRepository.findAll();
        log.info("Getting all categories (count): {}", categories.size());

        log.info("Creating categories dtos list...");
        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(categoryMapper::mapToCategoryResponseDto)
                .toList();
        log.info("Creating categories dtos list completed successfully.");

        log.info("Returning categories dtos list...");
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        log.info("[REQUEST] Getting category by id: {}", categoryId);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_LOG_ERROR_MSG, categoryId);
                    return new ResourceNotFoundException(
                            CATEGORY_NOT_FOUND_EXCEPTION_MSG + categoryId);
                });
        log.info("Successfully fetched category with ID: {}", categoryId);
        return categoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategoryName(Long categoryId, CategoryNameRequestDto request) {
        log.info("[REQUEST] Updating category with ID: {} with data: {}", categoryId, request);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_LOG_ERROR_MSG, categoryId);
                    return new ResourceNotFoundException(
                            CATEGORY_NOT_FOUND_EXCEPTION_MSG + categoryId);
                });
        category.setName(request.categoryName());
        category = categoryRepository.save(category);
        log.info("Category updated successfully: {}", category.getName());
        return categoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.info("[REQUEST] Deleting category with id: {}", categoryId);
        categoryRepository
                .findById(categoryId)
                .ifPresentOrElse(category -> {
                            categoryRepository.delete(category);
                            log.info("Category with id: {} deleted successfully", categoryId);
                        },
                        () -> {
                            log.error(CATEGORY_NOT_FOUND_LOG_ERROR_MSG, categoryId);
                            throw new ResourceNotFoundException(
                                    CATEGORY_NOT_FOUND_EXCEPTION_MSG + categoryId);
                        });
    }
}