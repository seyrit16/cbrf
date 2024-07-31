package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.AccountRestrictionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRestrictionListRepository extends JpaRepository<AccountRestrictionList, Long> {
    @Query(value = "select * from account_restriction_list", nativeQuery = true)
    List<AccountRestrictionList> findAll();

    @Query(value = "select * from account_restriction_list as arl where arl.id = :id limit 1", nativeQuery = true)
    Optional<AccountRestrictionList> findById(@Param("id") Long id);
}
