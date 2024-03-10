package com.menuapp.menuapp1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String feedBack;

}
