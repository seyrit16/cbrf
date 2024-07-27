package com.ocode.cbrf.service;

import com.ocode.cbrf.model.ED807;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ED807Service {
    ED807 save(ED807 ed807);
    void delete(Long id);
    ED807 update (ED807 ed807);
    Page<ED807> getByUser_Id(Long userId, Pageable pageable);
    Page<ED807> getByTitleContaining(Long userId, String title, Pageable pageable);
    Optional<ED807> getByTitle(Long userId, String title);
    Page<ED807> getBetweenDates(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<ED807> getBetweenCreationDateTime(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                           Pageable pageable);
    Page<ED807> getBetweenUploadDate(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
