package com.menuapp.menuapp1.controller;

import com.menuapp.menuapp1.dto.productDto.CreateProductDto;
import com.menuapp.menuapp1.dto.productDto.ResponseProductDto;
import com.menuapp.menuapp1.exceptions.ProductNotFoundException;
import com.menuapp.menuapp1.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
//"/api/product"
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@Tag(
        name = "CRUD REST APIs for Product Resource"
)
public class ProductController {

    private ProductService productService;

    @Operation(
            summary = "Create Product REST API",
            description = "Create Product REST API is used to save Products into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/save")
    public ResponseProductDto save(@RequestBody CreateProductDto createProductDto) {
        return productService.save(createProductDto);
    }

    @Operation(
            summary = "Get All Products REST API",
            description = "Get REST API used to fetch all the products from the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @GetMapping("/findall")
    public List<ResponseProductDto> findAll() {
        return productService.findAll();
    }


    @Operation(
            summary = "Find Product By ID",
            description = "GET REST API is used to fetch data for a product providing an ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @GetMapping("/findby/{id}")
    public ResponseEntity<ResponseProductDto> findById(@PathVariable Long id) {
        ResponseProductDto responseProductDto = productService.findById(id);
        return new ResponseEntity<>(responseProductDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Find Product By Id REST API",
            description = "GET METHOD REST API is used to get a single product from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseProductDto> updateById(@RequestBody CreateProductDto createProductDto,
                                         @PathVariable("id") Long id) {



               ResponseProductDto product = productService.updateById(createProductDto, id);
        return new  ResponseEntity<>(product,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Product REST API",
            description = "Delete Product REST API is used to delete a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);

        return "Product with id " + id + " was deleted.";
    }

// Exception Handling in POSTMAN
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ProductNotFoundException  ex) {
        return ex.getMessage();  // You can customize the response body here
    }

}
