package com.mpm.beforeandafter.category.controller;


import com.mpm.beforeandafter.category.dto.CategoryNameRequestDTO;
import com.mpm.beforeandafter.category.dto.CategoryResponseDTO;
import com.mpm.beforeandafter.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryNameRequestDTO request) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<CategoryResponseDTO> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable("id") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PatchMapping("/{id}")
    public CategoryResponseDTO updateCategoryName(@PathVariable("id") Long categoryId,
                                                  @Valid @RequestBody CategoryNameRequestDTO request) {
        return categoryService.updateCategoryName(categoryId, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}