package com.ocode.cbrf.service;

import com.ocode.cbrf.model.user.Role;

import java.util.Optional;

public interface RoleService {
    void addRole(String role);
    void deleteRole(String role);
    Optional<Role> getRole(String role);
}
