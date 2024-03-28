package com.menuapp.menuapp1.mapper;

import com.menuapp.menuapp1.dto.rolesDto.CreateRoleDto;
import com.menuapp.menuapp1.dto.rolesDto.ResponseRoleDto;
import com.menuapp.menuapp1.dto.userDto.CreateUserDto;
import com.menuapp.menuapp1.dto.userDto.ResponseUserDto;
import com.menuapp.menuapp1.entity.Role;
import com.menuapp.menuapp1.entity.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserMapper {

    private RoleMapper roleMapper;

    public ResponseUserDto toUserDto(User user){
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getId());
        responseUserDto.setEmail(user.getEmail());
        responseUserDto.setPassword(user.getPassword());
        responseUserDto.setUsername(user.getUsername());

        Set<Role> roleList = user.getRoles();
        List<ResponseRoleDto> responseRoleDtos = new ArrayList<>();

        for(Role role: roleList){

            responseRoleDtos.add((roleMapper.toRoleDto(role)));
        }
        responseUserDto.setRoles(responseRoleDtos);

    return responseUserDto;}

    public User toEntity(CreateUserDto createUserDto){
        User userToSave = new User();
        userToSave.setEmail(createUserDto.getEmail());
        userToSave.setPassword(createUserDto.getPassword());
        userToSave.setUsername(createUserDto.getUsername());


        Set<Role> roleSet = new HashSet<>();
        List<CreateRoleDto> listOfRoleDto = createUserDto.getRoles();
        for(CreateRoleDto createRoleDto: listOfRoleDto){
            roleSet.add(roleMapper.toEntity(createRoleDto));
        }
        userToSave.setRoles(roleSet);
        return userToSave;
    }




}
