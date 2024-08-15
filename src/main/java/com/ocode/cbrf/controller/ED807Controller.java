package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.components.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.dto.mapper.ED807MapperImpl;
import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.service.impl.ED807ServiceImpl;
import com.ocode.cbrf.service.impl.UserServiceImpl;
import com.ocode.cbrf.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ed")
public class ED807Controller {
    @Autowired
    ED807ServiceImpl ed807Service;
    @Autowired
    ED807MapperImpl ed807Mapper;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserServiceImpl userService;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                         @RequestBody Map<String,String> data){
        try{
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user= userService.getUser(username).get();

            int status = ed807Service.update(user.getId(), data);
            return switch (status) {
                case (404) -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
                case (500) -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                default -> new ResponseEntity<>(HttpStatus.OK);
            };
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("edId") Long edId){
        try{
            ed807Service.delete(edId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/byUser")
    public List<ED807Dto> getByUser_Id(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try {
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user= userService.getUser(username).get();

            Page<ED807> edPage = ed807Service.getByUser_Id(user.getId(),user.getRole().getRole().equals("ADMIN"), pageable);
            List<ED807Dto> edDtoList = new ArrayList<>();
            for(ED807 ed: edPage) {
                ed.setBicDirectoryEntries(new ArrayList<>());
                edDtoList.add(ed807Mapper.toDto(ed));
            }

            if(edDtoList.isEmpty())
                return null;



            return edDtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/serch/by_title")
    public List<ED807Dto> getByTitleContaining(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                               @RequestParam("title") String title, @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try {
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user= userService.getUser(username).get();

            Page<ED807> edPage = ed807Service.getByTitleContaining(user.getId(),title,
                    user.getRole().getRole().equals("ADMIN"), pageable);
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
    public List<ED807Dto> getBetweenDates(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                          @RequestParam("startDate")LocalDate startDate,
                                               @RequestParam("endDate") LocalDate endDate,
                                               @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try {
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user= userService.getUser(username).get();

            Page<ED807> edPage = ed807Service.getBetweenDates(user.getId(), startDate, endDate,user.getRole().getRole().equals("ADMIN"), pageable);
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
        try {
            CbrfUserDetails userDetails = (CbrfUserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Page<ED807> edPage = ed807Service.getBetweenCreationDateTime(userDetails.getId(), startDateTime,
                    endDateTime, userDetails.isAdmin(), pageable);
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
        try {
            CbrfUserDetails userDetails = (CbrfUserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Page<ED807> edPage = ed807Service.getBetweenUploadDate(userDetails.getId(), startDate, endDate, userDetails.isAdmin(), pageable);
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
