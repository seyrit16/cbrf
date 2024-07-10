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

    @Query(value = "select * from participant_info as pi where pi.id = :id limit 1",nativeQuery = true)
    Optional<ParticipantInfo> findById(@Param("id") Long id);

    @Query(value = "select * from participant_info as pi where pi.name = :name limit 1",nativeQuery = true)
    Optional<ParticipantInfo> findByName(@Param("name") String name);

    @Query(value = "select * from participant_info as pi where pi.parent_bic = :parentBic",nativeQuery = true)
    List<ParticipantInfo> findAllByParentBIC(@Param("parentBic") Integer parentBIC);

    @Query(value = "select * from participant_info as pi where pi.date_in between :startDate and :endDate",nativeQuery = true)
    List<ParticipantInfo> findAllBetweenDateIn(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select * from participant_info as pi where pi.date_out between :startDate and :endDate", nativeQuery = true)
    List<ParticipantInfo> findAllBetweenDateOut(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
