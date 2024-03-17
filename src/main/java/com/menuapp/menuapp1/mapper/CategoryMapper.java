package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.categoryDto.CreateCategoryDto;
import com.menuapp.menuapp1.dto.categoryDto.ResponseCategoryDto;
import com.menuapp.menuapp1.dto.productDto.ResponseProductDto;
import com.menuapp.menuapp1.entity.Category;
import com.menuapp.menuapp1.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component

public class CategoryMapper {



    public Category toEntity(CreateCategoryDto createCategoryDto){
        Category category = new Category();
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());
        return category;
    }

    public ResponseCategoryDto toDto(Category category)
    {
    ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto();

    responseCategoryDto.setId(category.getId());
    responseCategoryDto.setDescription(category.getDescription());
    responseCategoryDto.setName(category.getName());


        return responseCategoryDto;
    }


    }


