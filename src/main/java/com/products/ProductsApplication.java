package com.products;

import com.products.controller.CategoryController;
import com.products.model.Categories;
import com.products.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ProductsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductsApplication.class, args);
    }

}
