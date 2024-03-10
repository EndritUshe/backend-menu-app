package com.menuapp.menuapp1.repository;

import com.menuapp.menuapp1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
