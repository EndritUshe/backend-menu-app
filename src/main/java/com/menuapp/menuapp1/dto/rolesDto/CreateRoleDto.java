package com.menuapp.menuapp1.dto.rolesDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleDto {

    @NotNull(message = "The role name should not be blank!")
    private String roleName;
}
