package com.menuapp.menuapp1.dto.reviewDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseReviewDto {

    private long id;
    private String name;
    private String email;
    private String description;
    private long productId;
}