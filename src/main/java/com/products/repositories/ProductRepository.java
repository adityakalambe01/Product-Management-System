package com.products.repositories;

import com.products.model.Categories;
import com.products.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    void deleteByCategory(Categories categories);
}
