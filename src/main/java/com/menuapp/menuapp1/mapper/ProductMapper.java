package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.CreateProductDto;
import com.menuapp.menuapp1.dto.ResponseProductDto;
import com.menuapp.menuapp1.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ResponseProductDto toProductDto(Product product){
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setId(product.getId());
        responseProductDto.setName(product.getName());
        responseProductDto.setDescription(product.getDescription());
        responseProductDto.setPrice(product.getPrice());
        return responseProductDto;
    }

    public Product toEntity(CreateProductDto createProductDto){
        Product productToSave = new Product();
        productToSave.setName(createProductDto.getName());
        productToSave.setPrice(createProductDto.getPrice());
        productToSave.setDescription(createProductDto.getDescription());
        return productToSave;
    }



}
