package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.exception.ResourceNotFoundException;
import com.ocode.cbrf.invariants.CreationReason;
import com.ocode.cbrf.invariants.InfoTypeCode;
import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.repository.ED807Repository;
import com.ocode.cbrf.service.ED807Service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ED807ServiceImpl implements ED807Service {
    private final ED807Repository ed807Repository;

    @Autowired
    public ED807ServiceImpl(ED807Repository ed807Repository) {
        this.ed807Repository = ed807Repository;
    }

    @Override
    @Transactional
    public ED807 save(ED807 ed807) {
        return ed807Repository.save(ed807);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<ED807> optionalED807 = ed807Repository.findById(id);
        ED807 ed807 = optionalED807.orElse(null);
        if (ed807 != null) {
            ed807.setDeleted(true);
            ed807.setTitle(ed807.getTitle() + " - удалено: " + LocalDate.now());
            save(ed807);
        }
    }

    @Override
    @Transactional
    public void update(Long userId, Map<String, String> data) {
            Long id = Long.valueOf(data.get("id"));
            ED807 ed807 = getById(id).orElse(null);
            if (ed807 == null)
                throw new ResourceNotFoundException("there ed is no such");

            Optional.ofNullable(data.get("title")).ifPresent(title -> {
                try{
                    getByTitle(userId,title,true);
                }catch (ResourceNotFoundException ex){
                    ed807.setTitle(title);
                }
            });
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
    }

    @Override
    public Optional<ED807> getById(Long id) {
        Optional<ED807> opEd=ed807Repository.findById(id);
        if(opEd.isEmpty()) throw new ResourceNotFoundException("ed is not found");
        return opEd;
    }

    @Override
    public Page<ED807> getByUser_Id(Long userId, Boolean showDeleted, Pageable pageable) {
        Page<ED807> edList = ed807Repository.findAll(userId,showDeleted, pageable);
        if(edList.isEmpty()) throw new ResourceNotFoundException("ed for user not found");
        return edList;
    }

    @Override
    public Page<ED807> getByTitleContaining(Long userId, String title, Boolean showDeleted, Pageable pageable) {
        Page<ED807> edList = ed807Repository.findByTitleContaining(userId, title,showDeleted,  pageable);
        if(edList.isEmpty()) throw new ResourceNotFoundException("ed ByTitleContaining for user not found");
        return edList;
    }

    @Override
    public Optional<ED807> getByTitle(Long userId, String title, Boolean showDeleted) {
       Optional<ED807> opEd = ed807Repository.findByTitle(userId, title,showDeleted);
        if(opEd.isEmpty()) throw new ResourceNotFoundException("ed by title for user not found");
        return opEd;
    }

    @Override
    public Page<ED807> getBetweenDates(Long userId, LocalDate startDate, LocalDate endDate, Boolean showDeleted, Pageable pageable) {
        Page<ED807> edList =  ed807Repository.findBetweenDates(userId, startDate, endDate,showDeleted,  pageable);
        if(edList.isEmpty()) throw new ResourceNotFoundException("ed BetweenDates for user not found");
        return edList;
    }

    @Override
    public Page<ED807> getBetweenCreationDateTime(Long userId, LocalDateTime startDateTime,
                                                  LocalDateTime endDateTime, Boolean showDeleted, Pageable pageable) {
        Page<ED807> edList = ed807Repository.findBetweenCreationDateTime(userId, startDateTime, endDateTime,showDeleted,  pageable);
        if(edList.isEmpty()) throw new ResourceNotFoundException("ed BetweenCreationDateTime for user not found");
        return edList;
    }

    @Override
    public Page<ED807> getBetweenUploadDate(Long userId, LocalDate startDate, LocalDate endDate, Boolean showDeleted, Pageable pageable) {
        Page<ED807> edList = ed807Repository.findBetweenUploadDate(userId, startDate, endDate,showDeleted,  pageable);
        if(edList.isEmpty()) throw new ResourceNotFoundException("ed BetweenUploadDate for user not found");
        return edList;
    }
}
