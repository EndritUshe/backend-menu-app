package com.menuapp.menuapp1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {

    private String description;
    private String name;

    private Double price;

}
