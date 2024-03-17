package com.menuapp.menuapp1.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
    public class CreateCategoryDto {

        @NotBlank(message = "Name of Category cannot be blank")
        @Size(max = 100, message = "Name cannot be longer than 100 characters")
        private String name;
        @NotBlank(message = "Category desc cannot be empty")
        private String description;


    }
