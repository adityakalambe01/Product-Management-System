package com.products.controller;

import com.products.model.Categories;
import com.products.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /*
    *
    * Add Category
    *
    * */
    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Categories newCategory){
        if(categoryService.addCategory(newCategory)){
            return ResponseEntity.ok("Category added successfully!");
        }
        return ResponseEntity.ok("Category already exists!");
    }

    /*
    *
    * Update Category
    *
    * */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id ,@RequestBody Categories updateCategory){
        if (categoryService.updateCategory(id, updateCategory)){
            return ResponseEntity.ok("Successfully updated category id " + id);
        }
        return ResponseEntity.ok("Invalid category id " + id);
    }

    /*
    *
    * Get All Categories
    *
    * */
    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories(
            @RequestParam(value = "page",defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer pageSize
    ){
        return ResponseEntity.ok(categoryService.getAllCategories(pageNumber, pageSize));
    }

    /*
    *
    * Get Category by id
    *
    * */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryByID(@PathVariable("id") Long categoryId){
        Categories dbCategory = categoryService.getCategoryById(categoryId);

        return (dbCategory == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid category id "+categoryId) : ResponseEntity.ok(dbCategory);
    }

    /*
    *
    * Delete Category
    *
    * */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(
            @PathVariable("id") Long categoryId,
            @RequestParam(value = "force", defaultValue = "false", required = false) boolean forceDelete
    ) {
        Boolean result = false;
        if (forceDelete) {
            result = categoryService.deleteCategoryById(categoryId, true);
            if (result) {
                return ResponseEntity.ok("Category and associated products deleted successfully!");
            } else {
                return ResponseEntity.internalServerError().body("Category not found or unable to delete.");
            }
        } else {
            result = categoryService.deleteCategoryById(categoryId, false);
            if (result == null) {
                return ResponseEntity.internalServerError().body("Products exist, so category cannot be deleted. Use force delete to delete categories as well as products.");
            } else if (result) {
                return ResponseEntity.ok("Category deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid category ID " + categoryId);
            }
        }
    }
}
