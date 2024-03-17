package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.reviewDto.CreateReviewDto;
import com.menuapp.menuapp1.dto.reviewDto.ResponseReviewDto;
import com.menuapp.menuapp1.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review mapToEntity(CreateReviewDto createReviewDto) {

        Review review = new Review();
        review.setName(createReviewDto.getName());
        review.setEmail(createReviewDto.getEmail());
        review.setDescription(createReviewDto.getDescription());
        return review;
    }


    public ResponseReviewDto mapToDto(Review review) {

        ResponseReviewDto responseReviewDto = new ResponseReviewDto();

        responseReviewDto.setId(review.getId());
        responseReviewDto.setName(review.getName());
        responseReviewDto.setEmail(review.getEmail());

        responseReviewDto.setDescription(review.getDescription());
        responseReviewDto.setProductId(review.getProduct().getId());

        return responseReviewDto;
    }


}
