package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.rolesDto.CreateRoleDto;
import com.menuapp.menuapp1.dto.rolesDto.ResponseRoleDto;
import com.menuapp.menuapp1.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(CreateRoleDto createRoleDto){
        Role role = new Role();
        role.setRoleName(createRoleDto.getRoleName());
        return role;

    }

    public ResponseRoleDto toRoleDto(Role role){
        ResponseRoleDto responseRoleDto = new ResponseRoleDto();

        responseRoleDto.setId(role.getId());
        responseRoleDto.setRoleName(role.getRoleName());
        return responseRoleDto;
    }

}
