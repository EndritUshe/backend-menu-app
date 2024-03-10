package com.menuapp.menuapp1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description can not be blank")
    private String description;
    @Min(value = 0, message = "The price of the product should be bigger than 0.")
    private Double price;
    @NotBlank(message = "The name of the product should be always entered!")
    private String name;

    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Review> reviews;
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Category category;

}
