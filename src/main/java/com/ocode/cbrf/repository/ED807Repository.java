package com.ocode.cbrf.repository;

import com.ocode.cbrf.model.ED807;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ED807Repository extends JpaRepository<ED807, Long> {
    @Query(value = "select * from ed807", nativeQuery = true)
    List<ED807> findAll();

    @Query(value = "select * from ed807 as ed where ed.number = :number", nativeQuery = true)
    ED807 findByNumber(@Param("number") Integer number);

    @Query(value = "select * from ed807 as ed where ed.date between :startDate and :endDate", nativeQuery = true)
    List<ED807> findAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select * from ed807 as ed where ed.author = :author", nativeQuery = true)
    List<ED807> findAllByAuthor(@Param("author") Long author);

    @Query(value = "select * from ed807 as ed where ed.receiver = :receiver", nativeQuery = true)
    List<ED807> findAllByReceiver(@Param("receiver") Long receiver);

    @Query(value = "select * from ed807 as ed where ed.creation_date_time between :startDateTime and :endDateTime",
            nativeQuery = true)
    List<ED807> findAllBetweenCreationDateTime(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
}
