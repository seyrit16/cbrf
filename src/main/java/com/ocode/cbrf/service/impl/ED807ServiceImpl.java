package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.repository.ED807Repository;
import com.ocode.cbrf.service.ED807Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ED807ServiceImpl implements ED807Service {
    @Autowired
    private ED807Repository ed807Repository;

    @Override
    public ED807 save(ED807 ed807) {
        return ed807Repository.save(ed807);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ED807 update(ED807 ed807) {
        return ed807Repository.save(ed807);
    }

    @Override
    public Page<ED807> getByUser_Id(Long userId, Pageable pageable) {
        return ed807Repository.findAll(userId, pageable);
    }

    @Override
    public Page<ED807> getByTitleContaining(Long userId, String title, Pageable pageable) {
        return ed807Repository.findByTitleContaining(userId, title, pageable);
    }

    @Override
    public Optional<ED807> getByTitle(Long userId, String title) {
        return ed807Repository.findByTitle(userId, title);
    }

    @Override
    public Page<ED807> getBetweenDates(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return ed807Repository.findBetweenDates(userId, startDate, endDate, pageable);
    }

    @Override
    public Page<ED807> getBetweenCreationDateTime(Long userId, LocalDateTime startDateTime,
                                                  LocalDateTime endDateTime, Pageable pageable) {
        return ed807Repository.findBetweenCreationDateTime(userId, startDateTime, endDateTime, pageable);
    }

    @Override
    public Page<ED807> getBetweenUploadDate(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return ed807Repository.findBetweenUploadDate(userId, startDate, endDate, pageable);
    }
}
