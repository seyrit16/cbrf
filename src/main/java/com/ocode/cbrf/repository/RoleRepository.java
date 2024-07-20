package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select r.role from role as r where r.id = :roleId", nativeQuery = true)
    Optional<String> findRoleByRoleId(@Param("roleId") Long roleId);
}
