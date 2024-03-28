package com.menuapp.menuapp1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendor> vendorList;

    //, fetch = FetchType.EAGER

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList;

    //, fetch = FetchType.EAGER
//    fetch = FetchType.EAGER

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;


//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Category category;

}
