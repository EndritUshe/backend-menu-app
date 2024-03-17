package com.menuapp.menuapp1.dto.productDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    @NotBlank(message = "Description can not be blank")
    private String description;
    @NotBlank(message = "The name of the product should be always entered!")
    private String name;
    @Min(value = 0, message = "The price of the product should be bigger than 0.")
    private Double price;

    private Long categoryId;

}
