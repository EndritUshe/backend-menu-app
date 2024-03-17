package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.reviewDto.CreateReviewDto;
import com.menuapp.menuapp1.dto.reviewDto.ResponseReviewDto;
import com.menuapp.menuapp1.entity.Product;
import com.menuapp.menuapp1.entity.Review;
import com.menuapp.menuapp1.mapper.ReviewMapper;
import com.menuapp.menuapp1.repository.ProductRepository;
import com.menuapp.menuapp1.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private ProductRepository productRepository;

    public ResponseReviewDto save(long productId, CreateReviewDto createReviewDto) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with id: " + productId + "was not found"));

        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Product with id: " + productId + "was not found");
        } else existingProduct.get();

        Review review = reviewMapper.mapToEntity(createReviewDto);
        review.setProduct(foundProduct);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.mapToDto(savedReview);
    }


    public List<ResponseReviewDto> findByProductId(long productId) {
        List<Review> reviewList = reviewRepository.findByProductId(productId);

        List<ResponseReviewDto> responseReviewDtos = new ArrayList<>();
        for (Review review : reviewList) {
            responseReviewDtos.add(reviewMapper.mapToDto(review));
        }
        return responseReviewDtos;

//        return reviewList.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
    }

    public ResponseReviewDto findByReviewId(long productId, long reviewId) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with id: " + productId + "was not found"));
        Review foundReview = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review with id: " + reviewId + " was not found"));

        if (!(foundReview.getProduct().getId() == foundProduct.getId())) {
            throw new RuntimeException("Product with id: " + productId + " doesn't corresponds to review with id: " + reviewId);
        }

        return reviewMapper.mapToDto(foundReview);
    }


    public ResponseReviewDto updateByReviewId(long productId, long reviewId, CreateReviewDto createReviewDto) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with id: " + productId + "was not found"));
        Review foundReview = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review with id: " + reviewId + " was not found"));

        if (!(foundReview.getProduct().getId() == foundProduct.getId())) {
            throw new RuntimeException("Product with id: " + productId + " doesn't corresponds to review with id: " + reviewId);
        }
        foundReview.setId(reviewId);
        foundReview.setDescription(createReviewDto.getDescription());
        foundReview.setName(createReviewDto.getName());
        foundReview.setEmail(createReviewDto.getEmail());
        foundReview.setProduct(foundProduct);
        Review savedReview = reviewRepository.save(foundReview);
        return reviewMapper.mapToDto(savedReview);
    }

    public void deleteById(long productId, long reviewId) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with id: " + productId + "was not found"));
        Review foundReview = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review with id: " + reviewId + " was not found"));

        if (!(foundReview.getProduct().getId() == foundProduct.getId())) {
            throw new RuntimeException("Product with id: " + productId + " doesn't corresponds to review with id: " + reviewId);
        }
       reviewRepository.delete(foundReview);
    }


}