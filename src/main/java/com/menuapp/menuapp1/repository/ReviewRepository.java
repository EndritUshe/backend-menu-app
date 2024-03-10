package com.menuapp.menuapp1.repository;

import com.menuapp.menuapp1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
