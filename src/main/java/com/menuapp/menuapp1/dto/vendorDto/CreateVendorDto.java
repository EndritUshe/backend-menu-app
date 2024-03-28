package com.menuapp.menuapp1.dto.vendorDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVendorDto {
    @NotEmpty(message = "Company Name cannot be empty")
    private String companyName;

    @NotEmpty(message = "Address cannot be empty")
    private String address;


    @NotEmpty(message = "Phone cannot be empty")
    @Size(min = 10, max = 20, message = "Description length must be between 10 and 1000 characters")
    private String phone;

}
