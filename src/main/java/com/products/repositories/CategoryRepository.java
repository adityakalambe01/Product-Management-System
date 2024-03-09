package com.products.repositories;

import com.products.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    Categories findByCategoryName(String categoryName);

    Categories findByCategoryNameContaining(String categoryName);
}
