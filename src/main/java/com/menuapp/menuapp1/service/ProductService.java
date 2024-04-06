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

import java.util.*;

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

    //Classic Method to take the data from a single query
//    public List<ResponseProductDto> searchProducts(String categoryName, String name, Double minPrice, Double maxPrice){
//        List<Product> productList = productRepository.searchProducts(categoryName,name,minPrice,maxPrice);
//        List<ResponseProductDto> productDtoList = new ArrayList<>();
//
//        for(Product product: productList){
//            productDtoList.add(productMapper.toProductDto(product));
//        }
//return productDtoList;
//    }


    //Method to take data filtered query by query-> added together in a set to remove duplicates and then returned to the user
    public List<ResponseProductDto> searchProducts(String categoryName, String name, Double minPrice, Double maxPrice){
        List<Product> productList = new ArrayList<>();
        productList.addAll(productRepository.findAllByCategoryName(categoryName));
        productList.addAll(productRepository.findAllByName(name));
        productList.addAll(productRepository.findAllByPriceBetween(minPrice,maxPrice));

        Set<Product> productSet = new HashSet<>(productList);
        List<Product> uniqueList = new ArrayList<>(productSet);
        List<ResponseProductDto> responseProductDtos = new ArrayList<>();
        for(Product product: uniqueList){
            responseProductDtos.add(productMapper.toProductDto(product));

        }

        return responseProductDtos;
    }

}
