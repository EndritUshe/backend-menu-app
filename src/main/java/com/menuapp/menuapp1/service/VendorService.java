package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.vendorDto.CreateVendorDto;
import com.menuapp.menuapp1.dto.vendorDto.ResponseVendorDto;
import com.menuapp.menuapp1.entity.Product;
import com.menuapp.menuapp1.entity.Vendor;
import com.menuapp.menuapp1.mapper.VendorMapper;
import com.menuapp.menuapp1.repository.ProductRepository;
import com.menuapp.menuapp1.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VendorService {

    private VendorRepository vendorRepository;
    private ProductRepository productRepository;
    private VendorMapper vendorMapper;

    public ResponseVendorDto save(long id, CreateVendorDto createVendorDto) {
        Product foundProduct = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product with id: " + id + " was not found!"));

        Vendor vendorToSave = vendorMapper.toEntity(createVendorDto);
        vendorToSave.setProduct(foundProduct);
        Vendor existVendor = vendorRepository.save(vendorToSave);
        return vendorMapper.toVendorDto(existVendor);

//         There is also this other way to return an Entity Vendor
//        vendorToSave.setPhone(createVendorDto.getPhone());
//        vendorToSave.setAddress(createVendorDto.getAddress());
//        vendorToSave.setCompanyName(createVendorDto.getCompanyName());
    }

    public List<ResponseVendorDto> findAll() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<ResponseVendorDto> responseVendorDtos = new ArrayList<>();
        for (Vendor vendor : vendors) {
            responseVendorDtos.add(vendorMapper.toVendorDto(vendor));
        }
        return responseVendorDtos;
    }

    public List<ResponseVendorDto> findByProductId(Long productId) {

//        Product product = productRepository.findById(productId).orElseThrow(
//                ()-> new RuntimeException("Product with id: " + productId + " was not found!"));
//        List<Vendor> vendors = product.getVendors();

        List<Vendor> vendors = vendorRepository.findByProductId(productId);
        List<ResponseVendorDto> responseVendorDtos = new ArrayList<>();
        for (Vendor vendor : vendors) {
            responseVendorDtos.add(vendorMapper.toVendorDto(vendor));
        }

        return responseVendorDtos;
    }

    public ResponseVendorDto findByVendorId(Long productId, Long vendorId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product with id: " + productId + " was not found!"));

        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(
                () -> new RuntimeException("Vendor with id: " + vendorId + " was not found!"));

        if (!(vendor.getProduct().getId() == product.getId())) {
            throw new RuntimeException("The product with id: " + productId + " doesnt correspond to the vendor with id: " + vendorId);

        }
        return vendorMapper.toVendorDto(vendor);
    }

    public ResponseVendorDto updateByVendorId(Long productId, Long vendorId, CreateVendorDto createVendorDto) {
        Product foundpProduct = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product with id: " + productId + " was not found!"));

        Vendor foundVendor = vendorRepository.findById(vendorId).orElseThrow(
                () -> new RuntimeException("Vendor with id: " + vendorId + " was not found!"));

        if (!(foundVendor.getProduct().getId() == foundpProduct.getId())) {
            throw new RuntimeException("The product with id: " + productId + " doesnt correspond to the vendor with id: " + vendorId);

        }
        foundVendor.setCompanyName(createVendorDto.getCompanyName());
        foundVendor.setAddress(createVendorDto.getAddress());
        foundVendor.setPhone(createVendorDto.getPhone());
        foundVendor.setProduct(foundpProduct);
        Vendor savedVendor = vendorRepository.save(foundVendor);
        return vendorMapper.toVendorDto(savedVendor);

    }

    public void deleteVendor(Long productId, Long vendorId){
        Product foundpProduct = productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product with id: " + productId + " was not found!"));

        Vendor foundVendor = vendorRepository.findById(vendorId).orElseThrow(
                () -> new RuntimeException("Vendor with id: " + vendorId + " was not found!"));

        if (!(foundVendor.getProduct().getId() == foundpProduct.getId())) {
            throw new RuntimeException("The product with id: " + productId + " doesnt correspond to the vendor with id: " + vendorId);

        }
        vendorRepository.delete(foundVendor);
    }


}






