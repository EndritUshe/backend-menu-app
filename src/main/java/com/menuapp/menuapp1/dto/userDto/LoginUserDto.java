package com.menuapp.menuapp1.dto.userDto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {

    @NotEmpty(message = "Cannot be null!")
    private String usernameOrEmail;

    @NotEmpty(message = "Cannot be null!")
    private String password;

}
