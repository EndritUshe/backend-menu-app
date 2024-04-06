package com.menuapp.menuapp1.service;

import com.menuapp.menuapp1.dto.userDto.LoginUserDto;
import com.menuapp.menuapp1.dto.userDto.RegisterUserDto;
import com.menuapp.menuapp1.entity.Role;
import com.menuapp.menuapp1.entity.User;
import com.menuapp.menuapp1.repository.RoleRepository;
import com.menuapp.menuapp1.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    //Authentication manager controlls if a user exists and saves the user at SecurityContextHolder
    public Authentication login(LoginUserDto loginUserDto){
      Authentication auth = authenticationManager.authenticate
              (new UsernamePasswordAuthenticationToken
                (loginUserDto.getPassword(),loginUserDto.getUsernameOrEmail()));
              SecurityContextHolder.getContext().setAuthentication(auth);
              return auth;
}
        @Transactional
        public User register(RegisterUserDto registerUserDto){
        if(!(userRepository.existsByEmail
                (registerUserDto.getEmail()) || userRepository.existsByUsername(registerUserDto.getUsername()))){
            User userToSave = new User();
            userToSave.setEmail(registerUserDto.getEmail());
            userToSave.setUsername(registerUserDto.getUsername());
            userToSave.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role firstRole = roleRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(()->new RuntimeException("Role not found!"));
            roles.add(firstRole);
            userToSave.setRoles(roles);
            User usertoShow = userRepository.save(userToSave);
            return usertoShow;
        }else{
            throw new RuntimeException("User already exists!");
        }
    }
}
