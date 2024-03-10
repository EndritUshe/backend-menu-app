package com.menuapp.menuapp1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Double price;
    private String imageUrl;

    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Review> reviews;
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Category category;

}
