package com.ocode.cbrf.service;

import com.ocode.cbrf.model.ED807;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ED807Service {
    ED807 save(ED807 ed807);
    void delete(Long id);
    List<ED807> getAll();
    Optional<ED807> getByNumber(Integer number);
    List<ED807> getAllBetweenDates(LocalDate startDate,LocalDate endDate);
    List<ED807> getAllByAuthor(Long author);
    List<ED807> getAllByReceiver(Long receiver);
    List<ED807> getAllByCreationDateTime(LocalDateTime startDateTime,LocalDateTime endDateTime);
}
