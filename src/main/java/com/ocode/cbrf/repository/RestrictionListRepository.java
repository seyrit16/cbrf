package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.RestrictionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestrictionListRepository extends JpaRepository<RestrictionList, Long> {
    @Query(value = "select * from restriction_list",nativeQuery = true)
    List<RestrictionList> findAll();

    @Query(value = "select * from restriction_list as rl where rl.id = :id limit 1",nativeQuery = true)
    Optional<RestrictionList> findById(@Param("id") Long id);

    @Query(value = "select * from restriction_list as rl where rl.date between :startDate and :endDate", nativeQuery = true)
    List<RestrictionList> findAllBetweenDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select * from restriction_list as rl where rl.restriction = :restriction limit 1",nativeQuery = true)
    Optional<RestrictionList> findByRestriction(@Param("restriction") String restriction);
}
