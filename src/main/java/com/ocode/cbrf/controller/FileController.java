package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.*;
import com.ocode.cbrf.model.*;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.service.impl.*;
import com.ocode.cbrf.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/file")
public class FileController {
    final FileServiceImpl fileService;
    final ED807ServiceImpl ed807Service;
    final BICDirectoryEntryServiceImpl bicDirectoryEntryService;
    final ParticipantInfoServiceImpl participantInfoService;
    final AccountsServiceImpl accountsService;
    final RestrictionListServiceImpl restrictionListService;
    final AccountRestrictionListServiceImpl accountRestrictionListService;
    final SWBICSServiceImpl swbicsService;
    final DtoServiceImpl dtoService;
    final UserServiceImpl userService;
    final JwtService jwtService;

    @Autowired
    public FileController(FileServiceImpl fileService, ED807ServiceImpl ed807Service,
                          BICDirectoryEntryServiceImpl bicDirectoryEntryService,
                          ParticipantInfoServiceImpl participantInfoService, AccountsServiceImpl accountsService,
                          RestrictionListServiceImpl restrictionListService,
                          AccountRestrictionListServiceImpl accountRestrictionListService,
                          SWBICSServiceImpl swbicsService, DtoServiceImpl dtoService, UserServiceImpl userService,
                          JwtService jwtService) {
        this.fileService = fileService;
        this.ed807Service = ed807Service;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.participantInfoService = participantInfoService;
        this.accountsService = accountsService;
        this.restrictionListService = restrictionListService;
        this.accountRestrictionListService = accountRestrictionListService;
        this.swbicsService = swbicsService;
        this.dtoService = dtoService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                             @RequestParam("title") String title,
                                             @RequestBody MultipartFile file) {
        try {
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);

            ED807Dto ed807Dto = fileService.unmarshalXml(fileService.convertMultipartFileToFile(file));
            ED807 ed807 = dtoService.toEntities(ed807Dto);

            List<BICDirectoryEntry> bicDirectoryEntries = ed807.getBicDirectoryEntries();
            if (bicDirectoryEntries != null) {
                bicDirectoryEntries.parallelStream().forEach(bde -> {
                    if (bde.getSwbicsList() != null) {
                        bde.getSwbicsList().parallelStream().forEach(swbicsService::save);
                    }
                    ParticipantInfo participantInfo =bde.getParticipantInfo();
                    if (participantInfo.getRestrictionLists() != null) {
                        participantInfo.getRestrictionLists().parallelStream().forEach(restrictionListService::save);
                    }
                    participantInfoService.save(participantInfo);

                    if (bde.getAccounts() != null) {
                        bde.getAccounts().parallelStream().forEach(account -> {
                            if (account.getAccountRestrictionLists() != null) {
                                account.getAccountRestrictionLists().parallelStream().forEach(accountRestrictionListService::save);
                            }
                            accountsService.save(account);
                        });
                    }
                    bde.setDeleted(false);
                });
                bicDirectoryEntryService.saveAll(bicDirectoryEntries);
            }
            ed807.setDeleted(false);
            ed807.setTitle(title);
            ed807.setFileName(file.getOriginalFilename());
            ed807.setUploadDate(LocalDate.now());
            ed807Service.save(ed807);

            User user = userService.getUser(username).get();
            Set<ED807> ed807Set = user.getEd807s() != null ? user.getEd807s() : new HashSet<>();
            ed807Set.add(ed807);
            user.setEd807s(ed807Set);
            userService.update(user);

            return new ResponseEntity<>(ed807.getBicDirectoryEntries().get(1).getBic().toString(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
