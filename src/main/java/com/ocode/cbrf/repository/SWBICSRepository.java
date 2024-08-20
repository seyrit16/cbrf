package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.SWBICS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SWBICSRepository extends JpaRepository<SWBICS, Long> {
    @Query(value = "select * from swbics",nativeQuery = true)
    List<SWBICS> findAll();

    @Query(value = "select * from swbics as s where s.id = :id limit 1", nativeQuery = true)
    Optional<SWBICS> findById(@Param("id") Long id);
}
