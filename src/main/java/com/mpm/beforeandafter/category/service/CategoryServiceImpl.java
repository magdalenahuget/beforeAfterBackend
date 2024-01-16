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
        log.info("[ACTION] Preparing category to store...");
        log.debug("[REQUEST] Category from request: {}", request);
        Category category = categoryMapper.mapToCategoryEntity(request);
        log.info("[ACTION] Saving category in database...");
        category = categoryRepository.save(category);
        log.debug("[RESPONSE] Created category: {}", category);
        log.info("[ACTION] Category preparation completed successfully.");
        return categoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        log.info("[ACTION] Fetching all categories...");
        List<Category> categories = categoryRepository.findAll();
        log.debug("[REQUEST] Getting all categories (count): {}", categories.size());
        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(categoryMapper::mapToCategoryResponseDto)
                .toList();
        log.info("[ACTION] Creating categories dtos list completed successfully.");
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        log.info("[ACTION] Fetching indicated category...");
        log.debug("[REQUEST] Getting category by id: {}", categoryId);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_LOG_ERROR_MSG, categoryId);
                    return new ResourceNotFoundException(
                            CATEGORY_NOT_FOUND_EXCEPTION_MSG + categoryId);
                });
        log.debug("[RESPONSE] Fetched category with ID: {}", categoryId);
        log.info("[ACTION] Category successfully retrieved.");
        return categoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategoryName(Long categoryId, CategoryNameRequestDto request) {
        log.info("[ACTION] Updating category with indicated ID.");
        log.info("[REQUEST] Category id: {} with data: {}", categoryId, request);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_LOG_ERROR_MSG, categoryId);
                    return new ResourceNotFoundException(
                            CATEGORY_NOT_FOUND_EXCEPTION_MSG + categoryId);
                });
        category.setName(request.categoryName());
        category = categoryRepository.save(category);
        log.debug("[RESPONSE] Category updated: {}", category.getName());
        log.info("[ACTION] Category successfully updated.");
        return categoryMapper.mapToCategoryResponseDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.info("[ACTION] Deleting indicated category...");
        log.debug("[REQUEST] Category with id: {}", categoryId);
        categoryRepository
                .findById(categoryId)
                .ifPresentOrElse(category -> {
                            categoryRepository.delete(category);
                            log.debug("[REQUEST] Deleted category id: {}", categoryId);
                            log.info("[ACTION] Indicated category deleted successfully.");
                        },
                        () -> {
                            log.warn(CATEGORY_NOT_FOUND_LOG_ERROR_MSG, categoryId);
                            throw new ResourceNotFoundException(
                                    CATEGORY_NOT_FOUND_EXCEPTION_MSG + categoryId);
                        });
    }
}