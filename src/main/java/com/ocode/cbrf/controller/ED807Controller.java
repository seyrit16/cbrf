package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.components.CbrfUserDetails;
import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.service.mapper.impl.ED807MapperImpl;
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
    private final ED807ServiceImpl ed807Service;
    private final ED807MapperImpl ed807Mapper;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    @Autowired
    public ED807Controller(ED807ServiceImpl ed807Service, ED807MapperImpl ed807Mapper, JwtService jwtService, UserServiceImpl userService) {
        this.ed807Service = ed807Service;
        this.ed807Mapper = ed807Mapper;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResultDTO<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                            @RequestBody Map<String,String> data){
        try{
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user= userService.getUser(username).get();

            ed807Service.update(user.getId(), data);
            return ResultDTO.EMPTY_OK_RESULT;
        }catch (NullPointerException nullE){
            nullE.printStackTrace();
            return ResultDTO.NOT_FOUND_RESULT;
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @DeleteMapping("/delete")
    public ResultDTO<?> delete(@RequestParam("edId") Long edId){
        try{
            ed807Service.delete(edId);
            return ResultDTO.EMPTY_OK_RESULT;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/byUser")
    public ResultDTO<List<ED807Dto>> getByUser_Id(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                          @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
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
                return ResultDTO.NOT_FOUND_RESULT;


            ResultDTO<List<ED807Dto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(edDtoList);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/serch/by_title")
    public ResultDTO<List<ED807Dto>> getByTitleContaining(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
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
                return ResultDTO.NOT_FOUND_RESULT;

            ResultDTO<List<ED807Dto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(edDtoList);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/between_date")
    public ResultDTO<List<ED807Dto>> getBetweenDates(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
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
                return ResultDTO.NOT_FOUND_RESULT;

            ResultDTO<List<ED807Dto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(edDtoList);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/between_creationDateTime")
    public ResultDTO<List<ED807Dto>> getBetweenDates(@RequestParam("startDateTime") LocalDateTime startDateTime,
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
                return ResultDTO.NOT_FOUND_RESULT;

            ResultDTO<List<ED807Dto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(edDtoList);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/between_upload_date")
    public ResultDTO<List<ED807Dto>> getBetweenUploadDate(@RequestParam("startDate")LocalDate startDate,
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
                return ResultDTO.NOT_FOUND_RESULT;

            ResultDTO<List<ED807Dto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(edDtoList);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }
}
