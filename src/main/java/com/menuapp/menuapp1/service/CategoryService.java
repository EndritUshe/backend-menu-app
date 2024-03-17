package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.categoryDto.CreateCategoryDto;
import com.menuapp.menuapp1.dto.categoryDto.ResponseCategoryDto;
import com.menuapp.menuapp1.entity.Category;
import com.menuapp.menuapp1.mapper.CategoryMapper;
import com.menuapp.menuapp1.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;



    public ResponseCategoryDto save(CreateCategoryDto createCategoryDto){
        Category newCategory = categoryMapper.toEntity(createCategoryDto);
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.toDto(savedCategory) ;

    }

    public List<ResponseCategoryDto> findAll(){
        List<Category> categoryList = categoryRepository.findAll();
        //Nje pyetje per klasen -> perse kur marrim liste kategorie nuk na vijne edhe lista me produkte?
        List<ResponseCategoryDto> responseCategoryDtoList = new ArrayList<>();
        for(Category category : categoryList){
            responseCategoryDtoList.add(categoryMapper.toDto(category));
        }

        return responseCategoryDtoList;

    }

    public ResponseCategoryDto findById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new RuntimeException("Category with id: " + categoryId + " was not found!"));
        return categoryMapper.toDto(category);
    }

    public ResponseCategoryDto update(CreateCategoryDto createCategoryDto, long categoryId ){
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new RuntimeException("Category with id: " + categoryId + " was not found!")
        );


        foundCategory.setName(createCategoryDto.getName());
        foundCategory.setDescription(createCategoryDto.getDescription());
         Category savedCategory = categoryRepository.save(foundCategory);

        return categoryMapper.toDto(savedCategory);
    }

    public void delete(Long categoryId){
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category with id: " + categoryId + " was not found!")
        );

        categoryRepository.delete(foundCategory);
    }





}
