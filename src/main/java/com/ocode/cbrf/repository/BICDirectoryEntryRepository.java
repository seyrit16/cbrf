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

    @Query(value = "select * from bic_directory_entry as bde where bde.id = :id",
            nativeQuery = true)
    Optional<BICDirectoryEntry> findById(@Param("id") Long id);

    @Query(value = "select bde.* \n" +
            "from bic_directory_entry as bde \n" +
            "where bde.ed807_id = :ed_id and bde.bic = :bic\n" +
            "and (:deleted = true or bde.deleted = false) \n" +
            "group by bde.id",
            nativeQuery = true)
    Optional<BICDirectoryEntry> findByBic(@Param("ed_id") Long edId,@Param("bic") Integer bic,
                                          @Param("deleted") Boolean showDeleted);

    @Query(value = "select bde.* \n" +
            "from bic_directory_entry as bde \n" +
            "where bde.ed807_id = :ed_id\n" +
            "and (:deleted = true or bde.deleted = false) \n" +
            "group by bde.id",
            nativeQuery = true)
    Page<BICDirectoryEntry> findByEd807_ID(@Param("ed_id") Long edId,@Param("deleted") Boolean showDeleted,
                                           Pageable pageable);

    @Query(value = "select bde.* \n" +
            "from bic_directory_entry as bde \n" +
            "inner join participant_info as pi \n" +
            " on pi.id = bde.participant_info_id \n" +
            "where bde.ed807_id = :ed_id \n" +
            "and (:pi_name is null or pi.name = :pi_name) \n" +
            "and (:pi_type is null or pi.participant_type = :pi_type) \n" +
            "and (:deleted = true or bde.deleted = false) \n" +
            "group by bde.id"
            , nativeQuery = true)
    Page<BICDirectoryEntry> findByEd807_IDAndParticipantInfo_NameAndParticipantInfo_ParticipantType(@Param("ed_id") Long edId,
                                                                                                    @Param("pi_name") String piName,
                                                                                                    @Param("pi_type") String piType,
                                                                                                    @Param("deleted") Boolean showDeleted,
                                                                                                    Pageable pageable);
}
