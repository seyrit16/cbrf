package com.ocode.cbrf.service;

import com.ocode.cbrf.model.ED807;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface ED807Service {
    ED807 save(ED807 ed807);
    void delete(Long id);
    void update (Long userId, Map<String,String> data);
    Optional<ED807> getById(Long id);
    Page<ED807> getByUser_Id(Long userId, Boolean showDeleted, Pageable pageable);
    Page<ED807> getByTitleContaining(Long userId, String title, Boolean showDeleted, Pageable pageable);
    Optional<ED807> getByTitle(Long userId, String title, Boolean showDeleted);
    Page<ED807> getBetweenDates(Long userId, LocalDate startDate, LocalDate endDate, Boolean showDeleted, Pageable pageable);
    Page<ED807> getBetweenCreationDateTime(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                           Boolean showDeleted, Pageable pageable);
    Page<ED807> getBetweenUploadDate(Long userId, LocalDate startDate, LocalDate endDate, Boolean showDeleted, Pageable pageable);
}
