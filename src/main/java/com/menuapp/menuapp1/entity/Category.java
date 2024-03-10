package com.menuapp.menuapp1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Product> productList;


}
