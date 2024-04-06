package com.menuapp.menuapp1.dto.userDto;

import com.menuapp.menuapp1.dto.rolesDto.CreateRoleDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterUserDto {


    @NotNull(message = "The User Name should not be blank!")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30 characters long.")
    private String username;


    @NotNull(message = "Password cannot be null!")
    @Size(min = 6, max = 100, message = "The password should be between 6 and 100 characters!")
    private String password;


    @NotNull(message = "Email cannot be blank!")
    @Email(message = "Email should be valid")
    private String email;

}
