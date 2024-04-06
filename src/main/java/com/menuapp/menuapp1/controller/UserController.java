package com.menuapp.menuapp1.controller;
import com.menuapp.menuapp1.dto.userDto.CreateUserDto;
import com.menuapp.menuapp1.dto.userDto.ResponseUserDto;
import com.menuapp.menuapp1.entity.User;
import com.menuapp.menuapp1.service.UserService;
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
import java.util.Optional;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Users"
)
@SecurityRequirement(name = "basicAuth")
public class UserController {


    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save a user into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/save")
    public ResponseEntity<ResponseUserDto> registerUser(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>( userService.save(createUserDto),HttpStatus.CREATED);
    }


    @Operation(
            summary = "Find All Users REST API",
            description = "Find All Users REST API is used to fetch all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/findAll")
    public ResponseEntity<List<ResponseUserDto>> findAll() {
        List<ResponseUserDto> responseUserDtos = userService.findAll();
        return new ResponseEntity<>(responseUserDtos,HttpStatus.OK);
    }


    @Operation(
            summary = "Find User REST API",
            description = "Find User REST API is used to fetch user by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
   @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id) {
        ResponseUserDto responseUserDto = userService.findById(id);
        return new ResponseEntity<>(responseUserDto,HttpStatus.OK);
    }

//    @Operation(
//            summary = "Find User REST API",
//            description = "Find User REST API is used to fetch User by username from the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("/username/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        Optional<User> user = userService.getUserByUsername(username);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable Long id,
                                                      @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.update(id, createUserDto));
    }


    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
   @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
       userService.deleteById(id);
       return ResponseEntity.ok("User with id: " + id + " was successfully deleted!");

    }
//
//    @Operation(
//            summary = "Find User REST API",
//            description = "Find User REST API is used to fetch User by email or username from the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("/search/{username}/{email}")
//    public ResponseEntity<User> getUserByUsernameOrEmail(@PathVariable String username, @PathVariable String email) {
//        Optional<User> user = userService.findByUsernameOrEmail(username, email);
//        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @Operation(
//            summary = "Find User REST API",
//            description = "Find User REST API is used to fetch User by email from the database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    @GetMapping("/email/{email}")
//    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//        Optional<User> userOptional = userService.getUserByEmail(email);
//        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }


    //Authentification -- verifikim (Who is the user?)
    //Authorization -- access ne role te ndryshme (role based/ what each role is allowed to do)
    //Principal -- currently logged in user in account
    //Granted Authority -- number of authorizations each role is allowed to do
    //Role -- group of authorities assign together

    //1-Knowledge based Authentification
    //2-Possession based authentification
    //3-Multi factor Authentification




}