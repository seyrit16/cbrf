package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.components.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.service.mapper.impl.ED807MapperImpl;
import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.service.impl.ED807ServiceImpl;
import com.ocode.cbrf.service.impl.UserServiceImpl;
import com.ocode.cbrf.service.web.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ed")
@Tag(name = "ED807 controller")
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
    @Operation(summary = "Update ED807 by user id")
    public void update(
            @Parameter(description = "JWT token")
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example",
                                    description = "json may not contain all fields",
                                    value = """
                                            {
                                            "id": "string",
                                            "title": "string",
                                            "number": "string",
                                            "date": "string",
                                            "author": "string",
                                            "receiver": "string",
                                            "creationReason": "string",
                                            "creationDateTime": "string",
                                            "infoTypeCode": "string",
                                            "bussinessDay": "string",
                                            "directoryVersion": "string"
                                            }
                                            """)))
            @RequestBody Map<String, String> data) {
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user = userService.getUser(username).get();

            ed807Service.update(user.getId(), data);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete ED807 by id")
    public void delete(
            @Parameter(description = "ED807 id to delete")
            @RequestParam("edId") Long edId) {
        ed807Service.delete(edId);
    }

    @GetMapping("/search/byUser")
    @Operation(summary = "Get all ED807 by user id")
    public List<ED807Dto> getByUser_Id(
            @Parameter(description = "JWT token")
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,

            @Parameter(description = "Pagination")
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
        User user = userService.getUser(username).get();

        Page<ED807> edPage = ed807Service.getByUser_Id(user.getId(), user.getRole().getRole().equals("ADMIN"), pageable);
        List<ED807Dto> edDtoList = new ArrayList<>();
        for (ED807 ed : edPage) {
            ed.setBicDirectoryEntries(new ArrayList<>());
            edDtoList.add(ed807Mapper.toDto(ed));
        }

        return edDtoList;
    }

    @GetMapping("/search/by_title")
    @Operation(summary = "Get ED807 by title")
    public List<ED807Dto> getByTitleContaining(
            @Parameter(description = "JWT token")
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,

            @Parameter(description = "ED807 title to search")
            @RequestParam("title") String title,

            @Parameter(description = "Pagination")
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
        User user = userService.getUser(username).get();

        Page<ED807> edPage = ed807Service.getByTitleContaining(user.getId(), title,
                user.getRole().getRole().equals("ADMIN"), pageable);
        List<ED807Dto> edDtoList = new ArrayList<>();
        for (ED807 ed : edPage)
            edDtoList.add(ed807Mapper.toDto(ed));

        return edDtoList;
    }

    @GetMapping("/search/between_date")
    @Operation(summary = "Get all ED807 between dates")
    public List<ED807Dto> getBetweenDates(
            @Parameter(description = "JWT token")
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,

            @Parameter(description = "Start date to search")
            @RequestParam("startDate") LocalDate startDate,

            @Parameter(description = "End date to search")
            @RequestParam("endDate") LocalDate endDate,

            @Parameter(description = "Pagination")
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
        User user = userService.getUser(username).get();

        Page<ED807> edPage = ed807Service.getBetweenDates(user.getId(), startDate, endDate, user.getRole().getRole().equals("ADMIN"), pageable);
        List<ED807Dto> edDtoList = new ArrayList<>();
        for (ED807 ed : edPage)
            edDtoList.add(ed807Mapper.toDto(ed));

        return edDtoList;
    }

    @GetMapping("/search/between_creationDateTime")
    @Operation(summary = "Get all ED807 between creation dates")
    public List<ED807Dto> getBetweenDates(
            @Parameter(description = "Start date to search")
            @RequestParam("startDateTime") LocalDateTime startDateTime,

            @Parameter(description = "End date to search")
            @RequestParam("endDateTime") LocalDateTime endDateTime,

            @Parameter(description = "Pagination")
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        CbrfUserDetails userDetails = (CbrfUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Page<ED807> edPage = ed807Service.getBetweenCreationDateTime(userDetails.getId(), startDateTime,
                endDateTime, userDetails.isAdmin(), pageable);
        List<ED807Dto> edDtoList = new ArrayList<>();
        for (ED807 ed : edPage)
            edDtoList.add(ed807Mapper.toDto(ed));

        return edDtoList;
    }

    @GetMapping("/search/between_upload_date")
    @Operation(summary = "Get all ED807 between upload dates")
    public List<ED807Dto> getBetweenUploadDate(
            @Parameter(description = "Start date to search")
            @RequestParam("startDate") LocalDate startDate,

            @Parameter(description = "End date to search")
            @RequestParam("endDate") LocalDate endDate,

            @Parameter(description = "Pagination")
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        CbrfUserDetails userDetails = (CbrfUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Page<ED807> edPage = ed807Service.getBetweenUploadDate(userDetails.getId(), startDate, endDate, userDetails.isAdmin(), pageable);
        List<ED807Dto> edDtoList = new ArrayList<>();
        for (ED807 ed : edPage)
            edDtoList.add(ed807Mapper.toDto(ed));

        return edDtoList;
    }
}
