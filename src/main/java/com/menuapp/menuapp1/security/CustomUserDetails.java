package com.menuapp.menuapp1.security;

import com.menuapp.menuapp1.entity.Role;
import com.menuapp.menuapp1.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User userEntity;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Po mbush nje set me te gjitha rolet e nje perdoruesi
        Set<Role> setOfAllUserEntityRoles = userEntity.getRoles();
        //Liste bosh me access per cdo rol
        List<SimpleGrantedAuthority> listOfAllUserEntityAccesses = new ArrayList<>();
        //Iterojm gjithe rolet nje nga nje dhe marrim akseset per cdo rol te mundshem
        //Klasa SimpleGrantedAuthority menaxhohet nga SPRING dhe gjen cdo access ku ne kemi vendosur PreAuthorized ose RequestMatcher
        for(Role role: setOfAllUserEntityRoles){
            listOfAllUserEntityAccesses.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
    return listOfAllUserEntityAccesses;}

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
