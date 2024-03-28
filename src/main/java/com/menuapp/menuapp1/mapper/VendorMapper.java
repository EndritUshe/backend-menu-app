package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.vendorDto.CreateVendorDto;
import com.menuapp.menuapp1.dto.vendorDto.ResponseVendorDto;
import com.menuapp.menuapp1.entity.Vendor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class VendorMapper {


    public ResponseVendorDto toVendorDto(Vendor vendor){
        ResponseVendorDto responseVendorDto = new ResponseVendorDto();

        responseVendorDto.setId(vendor.getId());
        responseVendorDto.setAddress(vendor.getAddress());
        responseVendorDto.setPhone(vendor.getPhone());
        responseVendorDto.setCompanyName(vendor.getCompanyName());
        responseVendorDto.setProductId(vendor.getProduct().getId());
        return responseVendorDto;
    }

    public Vendor toEntity(CreateVendorDto createVendorDto){
        Vendor vendor = new Vendor();
        vendor.setPhone(createVendorDto.getPhone());
        vendor.setAddress(createVendorDto.getAddress());
        vendor.setCompanyName(createVendorDto.getCompanyName());
        return vendor;

    }
}
