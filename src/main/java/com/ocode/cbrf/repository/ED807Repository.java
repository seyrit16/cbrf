package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.ED807;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ED807Repository extends JpaRepository<ED807, Long> {
    @Query(value = "select * from ed807 as ed \n" +
            "inner join user_ed807 as u_ed \n" +
            " on u_ed.ed807_id =  ed.id \n" +
            "where u_ed.user_id = :user_id \n" +
            "and (:deleted = true or ed.deleted = false) \n" +
            "group by ed.id",
            nativeQuery = true)
    Page<ED807> findAll(@Param("user_id") Long userId, @Param("deleted") Boolean showDeleted, Pageable pageable);

    @Query(value = "select * from ed807 as ed where ed.id=:id",nativeQuery = true)
    Optional<ED807> findById(@Param("id") Long id);

    @Query(value = "select * from ed807 as ed \n" +
            "inner join user_ed807 as u_ed \n" +
            " on u_ed.ed807_id =  ed.id \n" +
            "where u_ed.user_id = :user_id and ed.title like :title \n" +
            "and (:deleted = true or ed.deleted = false) \n" +
            "group by ed.id",
            nativeQuery = true)
    Page<ED807> findByTitleContaining(@Param("user_id") Long userId, @Param("title") String Title,
                                      @Param("deleted") Boolean showDeleted, Pageable pageable);

    @Query(value = "select * from ed807 as ed \n" +
            "inner join user_ed807 as u_ed \n" +
            " on u_ed.ed807_id =  ed.id \n" +
            "where u_ed.user_id = :user_id and ed.title = :title \n" +
            "and (:deleted = true or ed.deleted = false) \n" +
            "group by ed.id",
            nativeQuery = true)
    Optional<ED807> findByTitle(@Param("user_id") Long userId, @Param("title") String Title, @Param("deleted") Boolean showDeleted);

    @Query(value = "select * from ed807 as ed \n" +
            "inner join user_ed807 as u_ed \n" +
            " on u_ed.ed807_id =  ed.id \n" +
            "where u_ed.user_id = :user_id and ed.date between :startDate and :endDate \n" +
            "and (:deleted = true or ed.deleted = false) \n" +
            "group by ed.id",
            nativeQuery = true)
    Page<ED807> findBetweenDates(@Param("user_id") Long userId, @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate, @Param("deleted") Boolean showDeleted,  Pageable pageable);

    @Query(value = "select * from ed807 as ed \n" +
            "inner join user_ed807 as u_ed \n" +
            " on u_ed.ed807_id =  ed.id \n" +
            "where u_ed.user_id = :user_id and ed.creation_date_time between :startDateTime and :endDateTime \n" +
            "and (:deleted = true or ed.deleted = false) \n" +
            "group by ed.id",
            nativeQuery = true)
    Page<ED807> findBetweenCreationDateTime(@Param("user_id") Long userId, @Param("startDateTime") LocalDateTime startDateTime,
                                            @Param("endDateTime") LocalDateTime endDateTime, @Param("deleted") Boolean showDeleted,  Pageable pageable);

    @Query(value = "select * from ed807 as ed \n" +
            "inner join user_ed807 as u_ed \n" +
            " on u_ed.ed807_id =  ed.id \n" +
            "where u_ed.user_id = :user_id and ed.upload_date between :startDate and :endDate \n" +
            "and (:deleted = true or ed.deleted = false) \n" +
            "group by ed.id",
            nativeQuery = true)
    Page<ED807> findBetweenUploadDate(@Param("user_id") Long userId, @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate, @Param("deleted") Boolean showDeleted,  Pageable pageable);
}
