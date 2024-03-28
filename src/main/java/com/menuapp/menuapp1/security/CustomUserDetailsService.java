package com.menuapp.menuapp1.security;

import com.menuapp.menuapp1.entity.User;
import com.menuapp.menuapp1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
//
//        Set<GrantedAuthority> authorities = userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
//
//        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authorities);
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//
//       User userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
//               .orElseThrow(()-> new UsernameNotFoundException
//                       ("User with: " + usernameOrEmail + " was not found!"));
//
       return new CustomUserDetails(userEntity);
//    }
}
}
