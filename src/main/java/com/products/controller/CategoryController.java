package com.products.controller;

import com.products.model.Categories;
import com.products.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories/")
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
    @PutMapping("{id}")
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
    public ResponseEntity<List<Categories>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /*
    *
    * Get Category by id
    *
    * */
    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoryByID(@PathVariable("id") Long categoryId){
        Categories dbCategory = categoryService.getCategoryById(categoryId);

        return (dbCategory == null) ? ResponseEntity.ok("Invalid category id "+categoryId) : ResponseEntity.ok(dbCategory);
    }

    /*
    *
    * Delete Category
    *
    * */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long categoryId){
        return categoryService.deleteCategoryById(categoryId) ?
                ResponseEntity.ok("Successfully deleted category having id "+categoryId) : ResponseEntity.ok("Invalid category id "+categoryId);
    }


}
