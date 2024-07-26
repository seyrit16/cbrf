package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Long> {
    @Query(value = "select * from bic_directory_entry", nativeQuery = true)
    List<BICDirectoryEntry> findAll();

    @Query(value = "select * from bic_directory_entry as bde where bde.bic = :bic limit 1",nativeQuery = true)
    Optional<BICDirectoryEntry> findByBic(@Param("bic") Integer bic);

    @Query(value = "select bde.* from bic_directory_entry as bde inner join ed807_bic_directory_entry as ed_bde " +
            "where ed_bde.ed807_id = :ed_id", nativeQuery = true)
    Page<BICDirectoryEntry> findBICDirectoryEntriesByEd807_ID(@Param("ed_id") Long edId, Pageable pageable);
}
