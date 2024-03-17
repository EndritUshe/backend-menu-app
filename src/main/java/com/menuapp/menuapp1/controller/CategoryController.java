package com.menuapp.menuapp1.controller;

import com.menuapp.menuapp1.dto.categoryDto.CreateCategoryDto;
import com.menuapp.menuapp1.dto.categoryDto.ResponseCategoryDto;
import com.menuapp.menuapp1.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {

    private CategoryService categoryService;


    @Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to save a category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/save")
    public ResponseEntity<ResponseCategoryDto> save(@RequestBody CreateCategoryDto createCategoryDto){
        return new  ResponseEntity<>(categoryService.save(createCategoryDto), HttpStatus.CREATED);
    }



    @Operation(
            summary = "Find All Categories REST API",
            description = "Find All Categories REST API is used to fetch all the categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/findall")
    public ResponseEntity<List<ResponseCategoryDto>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }



    @Operation(
            summary = "Find Categoriy REST API",
            description = "Find Category REST API is used to fetch category by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoryDto> findById(@PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.findById(categoryId),HttpStatus.OK);


    }

    @Operation(
            summary = "update Category REST API",
            description = "Update Category REST API is used to update a particular category in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseCategoryDto> update(@Valid @RequestBody CreateCategoryDto createCategoryDto, @PathVariable("id") long id) {
        return ResponseEntity.ok(categoryService.update(createCategoryDto, id));
    }


    @Operation(
            summary = "Delete Category REST API",
            description = "Delete Category REST API is used to delete a particular category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Category with id: "+ id+ " was successfully deleted!");
    }




}