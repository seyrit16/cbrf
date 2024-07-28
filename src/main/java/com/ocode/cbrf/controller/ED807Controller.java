package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.dto.mapper.ED807MapperImpl;
import com.ocode.cbrf.invariants.CreationReason;
import com.ocode.cbrf.invariants.InfoTypeCode;
import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.repository.UserRepository;
import com.ocode.cbrf.service.impl.ED807ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ed")
public class ED807Controller {
    @Autowired
    ED807ServiceImpl ed807Service;
    @Autowired
    ED807MapperImpl ed807Mapper;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Map<String,String> data){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = "";
        if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
            currentUsername = userDetails.getUsername();
        }
        User user = userRepository.findUserByLogin(currentUsername).get();

        try{
            String title = data.get("title");
            ED807 ed807 = ed807Service.getByTitle(user.getId(), title).orElse(null);
            if(ed807 == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Optional.ofNullable(data.get("newTitle")).ifPresent(ed807::setTitle);
            Optional.ofNullable(data.get("number")).map(Integer::parseInt).ifPresent(ed807::setNumber);
            Optional.ofNullable(data.get("date"))
                    .map(dateStr->LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .ifPresent(ed807::setDate);
            Optional.ofNullable(data.get("author")).map(Long::parseLong).ifPresent(ed807::setAuthor);
            Optional.ofNullable(data.get("receiver")).map(Long::parseLong).ifPresent(ed807::setReceiver);
            Optional.ofNullable(data.get("creationReason")).map(CreationReason::valueOf).ifPresent(ed807::setCreationReason);
            Optional.ofNullable(data.get("creationDatetime"))
                    .map(dateStr->OffsetDateTime.parse(dateStr,DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .ifPresent(ed807::setCreationDateTime);
            Optional.ofNullable(data.get("infoTypeCode")).map(InfoTypeCode::valueOf).ifPresent(ed807::setInfoTypeCode);
            Optional.ofNullable(data.get("businessDay"))
                    .map(dateStr->LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .ifPresent(ed807::setBusinessDay);
            Optional.ofNullable(data.get("directoryVersion")).map(Integer::parseInt).ifPresent(ed807::setDirectoryVersion);

            ed807Service.update(ed807);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/all")
    public List<ED807Dto> getByUser_Id(@PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = "";
        if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
            currentUsername = userDetails.getUsername();
        }
        User user = userRepository.findUserByLogin(currentUsername).get();

        try {
            Page<ED807> edPage = ed807Service.getByUser_Id(user.getId(), pageable);
            List<ED807Dto> edDtoList = new ArrayList<>();
            for(ED807 ed: edPage)
                edDtoList.add(ed807Mapper.toDto(ed));

            if(edDtoList.isEmpty())
                return null;

            return edDtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/serch/by_title")
    public List<ED807Dto> getByTitleContaining(@RequestParam("title") String title, @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = "";
        if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
            currentUsername = userDetails.getUsername();
        }
        User user = userRepository.findUserByLogin(currentUsername).get();

        try {
            Page<ED807> edPage = ed807Service.getByTitleContaining(user.getId(), title, pageable);
            List<ED807Dto> edDtoList = new ArrayList<>();
            for(ED807 ed: edPage)
                edDtoList.add(ed807Mapper.toDto(ed));

            if(edDtoList.isEmpty())
                return null;

            return edDtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/search/between_date")
    public List<ED807Dto> getBetweenDates(@RequestParam("startDate")LocalDate startDate,
                                               @RequestParam("endDate") LocalDate endDate,
                                               @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = "";
        if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
            currentUsername = userDetails.getUsername();
        }
        User user = userRepository.findUserByLogin(currentUsername).get();

        try {
            Page<ED807> edPage = ed807Service.getBetweenDates(user.getId(), startDate, endDate, pageable);
            List<ED807Dto> edDtoList = new ArrayList<>();
            for(ED807 ed: edPage)
                edDtoList.add(ed807Mapper.toDto(ed));

            if(edDtoList.isEmpty())
                return null;

            return edDtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/search/between_creationDateTime")
    public List<ED807Dto> getBetweenDates(@RequestParam("startDateTime") LocalDateTime startDateTime,
                                          @RequestParam("endDateTime") LocalDateTime endDateTime,
                                          @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = "";
        if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
            currentUsername = userDetails.getUsername();
        }
        User user = userRepository.findUserByLogin(currentUsername).get();

        try {
            Page<ED807> edPage = ed807Service.getBetweenCreationDateTime(user.getId(), startDateTime, endDateTime, pageable);
            List<ED807Dto> edDtoList = new ArrayList<>();
            for(ED807 ed: edPage)
                edDtoList.add(ed807Mapper.toDto(ed));

            if(edDtoList.isEmpty())
                return null;

            return edDtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/search/between_upload_date")
    public List<ED807Dto> getBetweenUploadDate(@RequestParam("startDate")LocalDate startDate,
                                          @RequestParam("endDate") LocalDate endDate,
                                          @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = "";
        if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
            currentUsername = userDetails.getUsername();
        }
        User user = userRepository.findUserByLogin(currentUsername).get();

        try {
            Page<ED807> edPage = ed807Service.getBetweenUploadDate(user.getId(), startDate, endDate, pageable);
            List<ED807Dto> edDtoList = new ArrayList<>();
            for(ED807 ed: edPage)
                edDtoList.add(ed807Mapper.toDto(ed));

            if(edDtoList.isEmpty())
                return null;

            return edDtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
