package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.user.Role;
import com.ocode.cbrf.repository.RoleRepository;
import com.ocode.cbrf.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void addRole(String role) {
        Role r = new Role();

        if (roleRepository.findRoleByRolename(role).isPresent()) {
            System.out.println("role is already exists");
            return;
        }

        Optional.ofNullable(role).ifPresentOrElse(rl -> {
            r.setRole(rl);
            roleRepository.save(r);
        }, () -> {
            throw new NullPointerException("role is null");
        });
    }

    @Override
    @Transactional
    public void deleteRole(String role) {
        roleRepository.findRoleByRolename(role).ifPresentOrElse(r -> {
            if (!roleRepository.existsUserWithRole(role))
                roleRepository.delete(r);
            else
                throw new RuntimeException("the role is referred to existing user or users");
        }, () -> {
            throw new EntityNotFoundException("role " + role + " does not exists");
        });
    }

    @Override
    public Optional<Role> getRole(String role) {
        return roleRepository.findRoleByRolename(role);
    }
}
