package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.productDto.CreateProductDto;

import com.menuapp.menuapp1.dto.productDto.ResponseProductDto;
import com.menuapp.menuapp1.entity.Category;
import com.menuapp.menuapp1.entity.Product;

import com.menuapp.menuapp1.exceptions.ProductNotFoundException;
import com.menuapp.menuapp1.mapper.ProductMapper;
import com.menuapp.menuapp1.repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    public ResponseProductDto save(CreateProductDto createProductDto){
        Category category = categoryRepository.findById(createProductDto.getCategoryId()).orElseThrow(
                () -> new RuntimeException("Category not found!")
        );
        Product newProduct = productMapper.toEntity(createProductDto);
        newProduct.setCategory(category);
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
    public ResponseProductDto findById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return productMapper.toProductDto(optionalProduct.get());
        }else {
            throw new ProductNotFoundException("Product with id: " + id + " does not exist.");
        }

    }

    public ResponseProductDto updateById(CreateProductDto createProductDto, Long id){

        Category foundCategory = categoryRepository.findById(createProductDto.getCategoryId()).orElseThrow(
                () -> new RuntimeException("Category with id: " + createProductDto.getCategoryId() + " was not found!"));

        Product productFound =   productRepository.findById(id).orElseThrow(
                () ->  new ProductNotFoundException("Product with id " + id + " was not found!")
        );
        productFound.setId(id);
        productFound.setName(createProductDto.getName());
        productFound.setPrice(createProductDto.getPrice());
        productFound.setDescription(createProductDto.getDescription());
        productFound.setCategory(foundCategory);
        Product savedProduct = productRepository.save(productFound);
        return productMapper.toProductDto(savedProduct);

    }

    public void deleteById(Long id){

        Product productFound = productRepository.findById(id).orElseThrow(
                ()-> new ProductNotFoundException("The Product with id " + id + " was not found. ")
        );
        productRepository.delete(productFound);

    }

}
