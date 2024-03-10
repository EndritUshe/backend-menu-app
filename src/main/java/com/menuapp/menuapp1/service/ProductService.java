package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.CreateProductDto;

import com.menuapp.menuapp1.dto.ResponseProductDto;
import com.menuapp.menuapp1.entity.Product;

import com.menuapp.menuapp1.mapper.ProductMapper;
import com.menuapp.menuapp1.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ResponseProductDto save(CreateProductDto createProductDto){
        Product newProduct = productMapper.toEntity(createProductDto);
        Product productToSave = productRepository.save(newProduct);
        return productMapper.toProductDto(productToSave);
    }

    public List<ResponseProductDto> findAll(){
        List<Product> listOfProducts = productRepository.findAll();
        List<ResponseProductDto> listOfDto = new ArrayList<>();
        for(Product product: listOfProducts){
            listOfDto.add(productMapper.toProductDto(product));
        }
        return listOfDto;
    }
    public Product findById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }else {
            throw new RuntimeException("Product with id: " + id + " does not exist.");
        }

    }

    public ResponseProductDto updateById(CreateProductDto createProductDto, Long id){
        Product productFound =   productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product with id " + id + " was not found!")
        );
        productFound.setName(createProductDto.getName());
        productFound.setPrice(createProductDto.getPrice());
        productFound.setDescription(createProductDto.getDescription());
        productRepository.save(productFound);
        return productMapper.toProductDto(productFound);

    }

    public void deleteById(Long id){
        Product productFound = productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("The Product with id " + id + " was deleted. ")
        );
        productRepository.delete(productFound);

    }

}
