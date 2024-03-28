package com.menuapp.menuapp1.controller;

import com.menuapp.menuapp1.dto.reviewDto.CreateReviewDto;
import com.menuapp.menuapp1.dto.reviewDto.ResponseReviewDto;
import com.menuapp.menuapp1.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Review Resource"
)
@SecurityRequirement(name = "basicAuth")
public class ReviewController {

    private ReviewService reviewService;


    @Operation(
            summary = "Leave a Review For a Particular Product REST API",
            description = "Leave a Review REST API is used to save a Review for a particular Product in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")

    @PostMapping("/save/{productId}")
    public ResponseEntity<ResponseReviewDto> save(@PathVariable("productId") Long productId, @Valid @RequestBody CreateReviewDto createReviewDto) {
        return new ResponseEntity<>(reviewService.save(productId, createReviewDto), HttpStatus.CREATED);

    }





    @Operation(
            summary = "Find All Reviews For a Particular Product REST API",
            description = "Find All Reviews REST API is used to fetch all the reviews for a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("findByProductId/{productId}")
    public ResponseEntity<List<ResponseReviewDto>> findByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(reviewService.findByProductId(productId));
    }


    @Operation(
            summary = "Find a Review By Id For a Particular Product REST API",
            description = "Find Review  By Id  For a Particular Product REST API is used to find a single review for a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("/findBy/{productId}/{reviewId}")
    public ResponseEntity<ResponseReviewDto> findByReviewId(@PathVariable("productId") Long productId,@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(reviewService.findByReviewId(productId,reviewId));
    }



    @Operation(
            summary = "Update a Review For a Particular Product REST API",
            description = "Update Review For a Particular Product  REST API is used to update a particular Review for a particular product  in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
//    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/update/{productId}/{reviewId}")
    public ResponseEntity<ResponseReviewDto> updateById(@PathVariable("productId") Long productId, @PathVariable("reviewId") Long reviewId,@Valid @RequestBody CreateReviewDto createReviewDto) {
        return ResponseEntity.ok(reviewService.updateByReviewId(productId,reviewId,createReviewDto));
    }


    @Operation(
            summary = "Delete a Review For a Particular Product REST API",
            description = "Delete Review For a Particular Product REST API is used to delete a particular Review for a particular Product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @DeleteMapping("/delete/{productId}/{reviewId}")
    public ResponseEntity<String> deleteById(@PathVariable("productId") Long productId,@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteById(productId,reviewId);
        return ResponseEntity.ok("Review successfully deleted!");
    }

}