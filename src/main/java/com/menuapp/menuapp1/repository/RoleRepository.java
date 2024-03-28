package com.menuapp.menuapp1.repository;

import com.menuapp.menuapp1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByRoleName (String role);
}
