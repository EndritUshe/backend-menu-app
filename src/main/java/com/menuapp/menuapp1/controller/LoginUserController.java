package com.menuapp.menuapp1.controller;

import com.menuapp.menuapp1.dto.userDto.LoginUserDto;
import com.menuapp.menuapp1.dto.userDto.RegisterUserDto;
import com.menuapp.menuapp1.entity.User;
import com.menuapp.menuapp1.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
//@SecurityRequirement(name = "basicAuth")
public class LoginUserController {

    private AuthenticationService authenticationService;



    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API is used to login"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/login")
    public ResponseEntity<Authentication> login( @Valid @RequestBody
                                    LoginUserDto loginUserDto){
       return ResponseEntity.ok(authenticationService.login(loginUserDto));
    }


    @Operation(
            summary = "Register REST API",
            description = "Register REST API is used to register a user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@Valid
                                             @RequestBody RegisterUserDto registerUserDto){

        return new ResponseEntity<>(authenticationService.register(registerUserDto), HttpStatus.CREATED);
    }

}
