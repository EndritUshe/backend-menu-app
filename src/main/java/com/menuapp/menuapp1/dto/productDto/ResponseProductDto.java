package com.menuapp.menuapp1.dto.productDto;

import com.menuapp.menuapp1.dto.reviewDto.ResponseReviewDto;
import com.menuapp.menuapp1.dto.vendorDto.ResponseVendorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ResponseProductDto {

    private Long id;

    private String description;
    private String name;

    private Double price;

    private List<ResponseReviewDto> reviewDtoList;
    private List<ResponseVendorDto> vendorDtoList;
    private Long categoryId;
    private String categoryName;
}
