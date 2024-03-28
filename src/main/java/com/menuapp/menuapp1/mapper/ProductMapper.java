package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.productDto.CreateProductDto;
import com.menuapp.menuapp1.dto.productDto.ResponseProductDto;
import com.menuapp.menuapp1.dto.reviewDto.ResponseReviewDto;
import com.menuapp.menuapp1.dto.vendorDto.ResponseVendorDto;
import com.menuapp.menuapp1.entity.Category;
import com.menuapp.menuapp1.entity.Product;
import com.menuapp.menuapp1.entity.Review;
import com.menuapp.menuapp1.entity.Vendor;
import com.menuapp.menuapp1.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class ProductMapper {

    private ReviewMapper reviewMapper;
    private VendorMapper vendorMapper;


    public ResponseProductDto toProductDto(Product product){
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setId(product.getId());
        responseProductDto.setName(product.getName());
        responseProductDto.setDescription(product.getDescription());
        responseProductDto.setPrice(product.getPrice());
        responseProductDto.setCategoryId(product.getCategory().getId());
        responseProductDto.setCategoryName(product.getCategory().getName());
        List<Review> reviewList = product.getReviewList();
        List<Vendor> vendors = product.getVendorList();
        if(reviewList != null && vendors != null){
            List<ResponseReviewDto> responseReviewDtos = new ArrayList<>();
            List<ResponseVendorDto> responseVendorDtos = new ArrayList<>();
            for(Review review: reviewList){
                responseReviewDtos.add(reviewMapper.mapToDto(review));
            }
            for(Vendor vendor: vendors){
                responseVendorDtos.add(vendorMapper.toVendorDto(vendor));
            }
            responseProductDto.setReviewDtoList(responseReviewDtos);
            responseProductDto.setVendorDtoList(responseVendorDtos);
        }else {
            responseProductDto.setReviewDtoList(Collections.emptyList());
            responseProductDto.setVendorDtoList(Collections.emptyList());
            responseProductDto.setId(product.getId());
            responseProductDto.setName(product.getName());
            responseProductDto.setDescription(product.getDescription());
            responseProductDto.setPrice(product.getPrice());
        }

        return responseProductDto;
    }


    public Product toEntity(CreateProductDto createProductDto){
        Product product = new Product();
        product.setName(createProductDto.getName());
        product.setPrice(createProductDto.getPrice());
        product.setDescription(createProductDto.getDescription());

        return product;
    }



//    public Book toEntity(CreateBookDto createBookDto) {
//        Book book = new Book();
//        book.setTitle(createBookDto.getTitle());
//        book.setAuthor(createBookDto.getAuthor());
//        book.setNumberOfPages(createBookDto.getNumberOfPages());
//
//        Category foundCategory = categoryRepository.findById(createBookDto.getCategoryId()).orElseThrow(
//                () -> new RuntimeException("Category was not found!")
//        );
////        Category foundCategory = categoryRepository.findById(createBookDto.getCategoryId()).orElseThrow(
////                () -> new RuntimeException("Category was not found!")
////        );
//        book.setCategory(foundCategory);
//        return book;
//    }

}
