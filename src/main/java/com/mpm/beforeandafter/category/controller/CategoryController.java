package com.mpm.beforeandafter.category.controller;


import com.mpm.beforeandafter.category.exception.CategoryNotFoundException;
import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category categoryDTO) {
        log.debug("Creating category: {}", categoryDTO);
        Category savedCategory = categoryService.createCategory(categoryDTO);
        log.info("Category created: {}", savedCategory);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        log.debug("Getting all categories:");
        List<Category> categoriesDTO = categoryService.getCategories();
        return ResponseEntity.ok(categoriesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long categoryId) {
        log.debug("Getting category by id: {}", categoryId);
        Category categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable("name") String categoryName) {
        log.debug("Getting category by name: {}", categoryName);
        return categoryService.getCategoryByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("No category found with name: " + categoryName));
    }

    @PatchMapping("{id}/name")
    public ResponseEntity<Category> updateCategoryName(@PathVariable("id") Long categoryId,
                                                       @RequestBody String categoryName) {
        log.debug("Updating category with id: {} with data: {}", categoryId, categoryName);
        Category categoryDTO = categoryService.updateCategoryName(categoryId, categoryName);
        log.info("Category updated: {}", categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
        log.debug("Deleting category with id: {}", categoryId);
        categoryService.deleteCategory(categoryId);
        log.info("Category with id: {} deleted successfully", categoryId);
        return ResponseEntity.ok("Category deleted successfully!");
    }
}