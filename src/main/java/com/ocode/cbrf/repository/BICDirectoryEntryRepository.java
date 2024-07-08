package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.BICDirectoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Long> {
    @Query(value = "select * from bic_directory_entry", nativeQuery = true)
    List<BICDirectoryEntry> findAll();

    @Query(value = "select * from bic_directory_entry as bde where bde.bic = :bic",nativeQuery = true)
    BICDirectoryEntry findByBic(@Param("bic") Integer bic);
}
