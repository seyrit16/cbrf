package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.ParticipantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfo, Long> {
    @Query(value = "select * from participant_info",nativeQuery = true)
    List<ParticipantInfo> findAll();

    @Query(value = "select * from participant_info as pi pi.id = :id limit 1",
            nativeQuery = true)
    Optional<ParticipantInfo> findById(@Param("id") Long id);
}
