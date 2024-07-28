package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.CreationReason;
import com.ocode.cbrf.invariants.InfoTypeCode;
import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.repository.ED807Repository;
import com.ocode.cbrf.service.ED807Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
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
    public int update(Long userId,Map<String,String> data) {
        try{
            String title = data.get("title");
            ED807 ed807 = getByTitle(userId, title).orElse(null);
            if (ed807 == null)
                return 404;

            Optional.ofNullable(data.get("newTitle")).ifPresent(ed807::setTitle);
            Optional.ofNullable(data.get("number")).map(Integer::parseInt).ifPresent(ed807::setNumber);
            Optional.ofNullable(data.get("date"))
                    .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .ifPresent(ed807::setDate);
            Optional.ofNullable(data.get("author")).map(Long::parseLong).ifPresent(ed807::setAuthor);
            Optional.ofNullable(data.get("receiver")).map(Long::parseLong).ifPresent(ed807::setReceiver);
            Optional.ofNullable(data.get("creationReason")).map(CreationReason::valueOf).ifPresent(ed807::setCreationReason);
            Optional.ofNullable(data.get("creationDatetime"))
                    .map(dateStr -> OffsetDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .ifPresent(ed807::setCreationDateTime);
            Optional.ofNullable(data.get("infoTypeCode")).map(InfoTypeCode::valueOf).ifPresent(ed807::setInfoTypeCode);
            Optional.ofNullable(data.get("businessDay"))
                    .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .ifPresent(ed807::setBusinessDay);
            Optional.ofNullable(data.get("directoryVersion")).map(Integer::parseInt).ifPresent(ed807::setDirectoryVersion);

            ed807Repository.save(ed807);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
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
