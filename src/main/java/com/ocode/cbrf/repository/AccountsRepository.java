package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
    @Query(value = "select * from accounts", nativeQuery = true)
    List<Accounts> findAll();

    @Query(value = "select * from accounts as a where a.id = :id limit 1",nativeQuery = true)
    Optional<Accounts> findById(@Param("id") Long id);

    @Query(value = "select * from accounts as a where a.account = :account limit 1", nativeQuery = true)
    Optional<Accounts> findByAccount(@Param("account") String account);

    @Query(value = "select * from accounts as a where a.account_cbr_bic = :accountCbrBic",nativeQuery = true)
    List<Accounts> findAllByAccountCBRBIC(@Param("accountCbrBic") Integer accountCbrBic);

    @Query(value = "select * from accounts as a where a.date_in between :startDate and :endDate",nativeQuery = true)
    List<Accounts> findAllBetweenDateIn(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select * from accounts as a where a.date_out between :startDate and :endDate",nativeQuery = true)
    List<Accounts> findAllBetweenDateOut(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
