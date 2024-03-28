package com.menuapp.menuapp1.dto.userDto;

import com.menuapp.menuapp1.dto.rolesDto.ResponseRoleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseUserDto {

    private Long id;

    private String username;

    private String password;
    private String email;
    private List<ResponseRoleDto> roles;


}
