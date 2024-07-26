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
    Page<BICDirectoryEntry> findByEd807_ID(@Param("ed_id") Long edId, Pageable pageable);

    @Query(value = "select bde.* \n" +
            "from bic_directory_entry as bde \n" +
            "inner join ed807_bic_directory_entry as ed_bde \n" +
            " on ed_bde.bic_directory_entry_id = bde.id \n" +
            "inner join participant_info as pi \n" +
            " on pi.id = bde.participant_info_id \n" +
            "where ed_bde.ed807_id = :ed_id \n" +
            "and (:pi_name is null or pi.name = :pi_name) \n" +
            "and (:pi_type is null or pi.participant_type = :pi_type) \n" +
            "group by bde.bic"
            , nativeQuery = true)
    Page<BICDirectoryEntry> findByEd807_IDAndParticipantInfo_NameAndParticipantInfo_ParticipantType(@Param("ed_id") Long edId,
                                                                                                    @Param("pi_name") String piName,
                                                                                                    @Param("pi_type") String piType,
                                                                                                    Pageable pageable);
}
