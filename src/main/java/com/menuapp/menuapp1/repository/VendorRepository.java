package com.menuapp.menuapp1.repository;

import com.menuapp.menuapp1.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    List<Vendor> findByProductId(Long id);
}
