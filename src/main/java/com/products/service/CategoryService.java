package com.products.service;

import com.products.model.Categories;
import com.products.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /*
    *
    * Get all Categories
    *
    * */
    public List<Categories> getAllCategories(Integer pageNumber, Integer pageSize){
        return   categoryRepository.findAll(PageRequest.of(pageNumber, pageSize))
                                   .getContent();
    }

    /*
    *
    * Get Category by Id
    *
    * */
    public Categories getCategoryById(long categoryID){
        Categories categories;
        try{
            categories = categoryRepository.findById(categoryID).get();
        }catch(Exception e){
            return null;
        }
        return categories;
    }

    /*
    *
    * Delete Category by Id
    *
    * */
    public Boolean deleteCategoryById(long categoryID){

        try{
            Categories categories = categoryRepository.findById(categoryID).get();
            categoryRepository.delete(categories);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Add Category
    *
    * */
    public Boolean addCategory(Categories category){
        try {
            Categories dbCategory = categoryRepository.findByCategoryName(category.getCategoryName().trim());
            if (dbCategory != null)
                throw new IllegalArgumentException("Category already exists!");
            categoryRepository.save(category);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /*
    *
    * Update Category
    *
    * */
    public Boolean updateCategory(Long categoryIdFromBrowser ,Categories updateCategory){
        try {
            Categories dbCategory = categoryRepository.findById(categoryIdFromBrowser).orElse(null);
            if (dbCategory == null)
                throw new IllegalArgumentException("Category not found!");
            dbCategory.setCategoryName(updateCategory.getCategoryName());
            dbCategory.setCategoryDescription(updateCategory.getCategoryDescription());
            categoryRepository.save(dbCategory);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    @PostConstruct
    public void addDefaultCategories() {
        if (categoryRepository.count() == 0) {
            Map<String,String> productCategories= new HashMap<>();

            productCategories.put("Electronics and Gadgets", "Including smartphones, laptops, tablets, cameras, and accessories like chargers and headphones.");
            productCategories.put("Apparel and Accessories", "Clothing items, shoes, bags, jewelry, and other fashion accessories.");
            productCategories.put("Home and Kitchen Appliances", "Appliances for cooking, cleaning, and home improvement, such as refrigerators, washing machines, vacuum cleaners, and kitchen gadgets.");
            productCategories.put("Health and Beauty Products", "Including skincare products, cosmetics, hair care items, vitamins, and supplements.");
            productCategories.put("Sports and Outdoor Equipment", "Gear for various sports activities, camping equipment, hiking gear, bicycles, and fitness accessories.");
            productCategories.put("Toys and Games", "Including toys for children of all ages, board games, puzzles, and outdoor play equipment.");
            productCategories.put("Books and Stationery", "Books across various genres, notebooks, pens, office supplies, and art materials.");
            productCategories.put("Furniture and Home Decor", "Including indoor and outdoor furniture, home decor items like rugs, curtains, and wall art.");
            productCategories.put("Food and Beverages", "Grocery items, beverages, snacks, spices, and specialty foods.");
            productCategories.put("Automotive Parts and Accessories", "Parts for vehicles, car accessories, maintenance products, and automotive tools.");




            for (Map.Entry<String, String> entry : productCategories.entrySet()) {
                Categories newCategory = new Categories();
                newCategory.setCategoryName(entry.getKey());
                newCategory.setCategoryDescription(entry.getValue());
                System.out.println(newCategory);
                categoryRepository.save(newCategory);
            }
            System.out.println("Default categories added successfully");
        }
    }
}
