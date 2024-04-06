package com.menuapp.menuapp1.controller;

import com.menuapp.menuapp1.dto.vendorDto.CreateVendorDto;
import com.menuapp.menuapp1.dto.vendorDto.ResponseVendorDto;
import com.menuapp.menuapp1.service.VendorService;
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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/vendor")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Vendor Class"
)
//@SecurityRequirement(name = "basicAuth")
public class VendorController {

    private VendorService vendorService;


    @Operation(
            summary = "Vendors For a Particular Product REST API",
            description = "This REST API is used to save a Review for a particular Product in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @PostMapping("/save/{productId}")
    public ResponseEntity<ResponseVendorDto> save(@PathVariable("productId") Long productId,
                                                  @RequestBody CreateVendorDto createVendorDto) {
        return new ResponseEntity<>(vendorService.save(productId, createVendorDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Vendors For a Particular Product REST API",
            description = "This REST API is used to find all Vendors for a particular Product in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @GetMapping("/findAll")
    public ResponseEntity<List<ResponseVendorDto>> findAll() {
        return ResponseEntity.ok(vendorService.findAll());
    }


    @Operation(
            summary = "Find All Vendors For a Particular Product REST API",
            description = "Find All Vendors REST API is used to fetch all the vendors for a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/findByProductId/{productId}")
    public ResponseEntity<List<ResponseVendorDto>> findByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(vendorService.findByProductId(productId));
    }


    @Operation(
            summary = "Find A particular Vendor For a Particular Product REST API",
            description = "Find A Vendor REST API is used to fetch all the vendors for a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/findBy/{productId}/{vendorId}")
    public ResponseEntity<ResponseVendorDto> findByVendorId(@PathVariable("productId") Long productId,
                                                            @PathVariable("vendorId") Long vendorId) {
        return ResponseEntity.ok(vendorService.findByVendorId(productId, vendorId));
    }


    @Operation(
            summary = "Update A particular Vendor For a Particular Product REST API",
            description = "Update A Vendor REST API is used to save a vendor for a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @PutMapping("/update/{productId}/{vendorId}")
    public ResponseEntity<ResponseVendorDto> updateByVendorId(@PathVariable Long productId,
                                                              @PathVariable Long vendorId,
                                                              @RequestBody CreateVendorDto createVendorDto) {

        return ResponseEntity.ok(vendorService.updateByVendorId(productId, vendorId, createVendorDto));
    }


    @Operation(
            summary = "Delete a Vendor For a Particular Product REST API",
            description = "Delete Vendor For a Particular Product REST API is used to delete a particular Review for a particular Product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @DeleteMapping("/delete/{productId}/{vendorId}")
    public ResponseEntity<String> deleteById(@PathVariable Long productId, @PathVariable Long vendorId) {
        vendorService.deleteVendor(productId, vendorId);
        return ResponseEntity.ok("Vendor was deleted successfully!");
    }


}
