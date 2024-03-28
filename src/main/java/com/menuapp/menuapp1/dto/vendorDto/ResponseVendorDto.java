package com.menuapp.menuapp1.dto.vendorDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVendorDto {

    private Long id;
    private String companyName;
    private String address;
    private String phone;
    private Long productId;

}
