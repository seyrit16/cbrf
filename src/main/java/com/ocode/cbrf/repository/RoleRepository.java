package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from role as r where r.role = :role", nativeQuery = true)
    Optional<Role> findRoleByRolename(@Param("role") String role);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM User u WHERE u.role = :role AND u.is_deleted = true", nativeQuery = true)
    Boolean existsUserWithRole(@Param("role") String role);
}
