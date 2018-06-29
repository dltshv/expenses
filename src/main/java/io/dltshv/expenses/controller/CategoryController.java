package io.dltshv.expenses.controller;

import io.dltshv.expenses.entity.Category;
import io.dltshv.expenses.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable("id") String id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createOrUpdateCategory(category);
    }

    @PutMapping("/category")
    public Category updateCategory(@RequestBody Category category) {
        if (category.getId() == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cannot update category without 'id provided'");
        }
        return categoryService.createOrUpdateCategory(category);
    }

}
