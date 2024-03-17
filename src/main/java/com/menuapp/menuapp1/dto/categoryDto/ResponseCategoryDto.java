package com.menuapp.menuapp1.dto.categoryDto;

import com.menuapp.menuapp1.dto.productDto.ResponseProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseCategoryDto {

    private Long id;
    private String name;
    private String description;
//    private List<ResponseProductDto> responseProductDtos;

}