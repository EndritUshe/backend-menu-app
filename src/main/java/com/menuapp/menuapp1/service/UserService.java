package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.rolesDto.CreateRoleDto;
import com.menuapp.menuapp1.dto.userDto.CreateUserDto;
import com.menuapp.menuapp1.dto.userDto.ResponseUserDto;
import com.menuapp.menuapp1.entity.User;
import com.menuapp.menuapp1.mapper.RoleMapper;
import com.menuapp.menuapp1.mapper.UserMapper;
import com.menuapp.menuapp1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private RoleMapper roleMapper;

    public ResponseUserDto save(CreateUserDto createUserDto){
        User userToSave = userMapper.toEntity(createUserDto);
       userToSave.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        User userToShow = userRepository.save(userToSave);
        return userMapper.toUserDto(userToShow);
    }

    public List<ResponseUserDto> findAll(){
        List<User> userList = userRepository.findAll();
        List<ResponseUserDto> responseUserDtoList = new ArrayList<>();

        for(User user: userList){
            responseUserDtoList.add(userMapper.toUserDto(user));
        }
        return responseUserDtoList;
    }

    public ResponseUserDto findById(Long id){
        User foundUser = userRepository.findById(id).orElseThrow(()->
        new RuntimeException("User with id: " + id + " was not found!"));

        return userMapper.toUserDto(foundUser);
    }

    public ResponseUserDto update(Long id, CreateUserDto createUserDto){
        User foundUser = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User with id: " + id + " was not found!"));
        foundUser.setId(id);
        foundUser.setUsername(createUserDto.getUsername());
        foundUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        foundUser.setEmail(createUserDto.getEmail());
        foundUser.setRoles(createUserDto.getRoles().stream().map(role -> roleMapper.toEntity(role)).collect(Collectors.toSet()));
        User savedUser = userRepository.save(foundUser);
        return userMapper.toUserDto(savedUser);
    }


    public void deleteById(Long id){
        User foundUser = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User with id: " + id + " was not found!"));
        userRepository.delete(foundUser);
    }
}
