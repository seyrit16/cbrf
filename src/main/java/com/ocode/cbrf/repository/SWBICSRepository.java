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

    @Query(value = "select * from swbics as s where s.swbic = :swbic limit 1", nativeQuery = true)
    Optional<SWBICS> findBySwbic(@Param("swbic") String swbic);

    @Query(value = "select * from swbics as s where s.default_swbic = :defaultSwbic",nativeQuery = true)
    List<SWBICS> findByDefaultSWBIC(@Param("defaultSwbic") boolean defaultSwbic);
}
