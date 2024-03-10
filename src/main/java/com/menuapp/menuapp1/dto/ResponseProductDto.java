package com.menuapp.menuapp1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDto {

    private Long id;

    private String description;
    private String name;

    private Double price;
}
