package com.menuapp.menuapp1.repository;

import com.menuapp.menuapp1.entity.Category;
import com.menuapp.menuapp1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "select  p from Product p left join p.category c where c.name like %?1% or p.name like %?2% or p.price >= ?3 and p.price <= ?4 ")
//    List<Product> searchProducts(String categoryName, String name, Double minPrice, Double maxPrice);


    @Query(value = "select p from Product p left join p.category c where c.name like %?1%")
    List<Product> findAllByCategoryName(String categoryName);

    List<Product> findAllByName(String name);


    List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);

}
