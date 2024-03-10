package com.menuapp.menuapp1.repository;

import com.menuapp.menuapp1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
