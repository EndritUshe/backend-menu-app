package com.menuapp.menuapp1.controller;
import com.menuapp.menuapp1.dto.CreateProductDto;
import com.menuapp.menuapp1.dto.ResponseProductDto;
import com.menuapp.menuapp1.entity.Product;
import com.menuapp.menuapp1.mapper.ProductMapper;
import com.menuapp.menuapp1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;



    @PostMapping("/save")
    public ResponseProductDto save(@RequestBody CreateProductDto createProductDto){
        return productService.save(createProductDto);
    }
    @GetMapping("/all")
    public List<ResponseProductDto> findAll(){
        return productService.findAll();
    }

    @PutMapping("product/{id}")
    public ResponseProductDto updateById(@RequestBody CreateProductDto createProductDto,
                              @PathVariable("id") Long id){


       return productService.updateById(createProductDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id")Long id){
        productService.deleteById(id);

        return "Product with id" + id + " was deleted.";
    }


}
