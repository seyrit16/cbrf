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
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api/file")
public class FileController {
    private final FileServiceImpl fileService;
    private final ED807ServiceImpl ed807Service;
    private final BICDirectoryEntryServiceImpl bicDirectoryEntryService;
    private final ParticipantInfoServiceImpl participantInfoService;
    private final AccountsServiceImpl accountsService;
    private final RestrictionListServiceImpl restrictionListService;
    private final AccountRestrictionListServiceImpl accountRestrictionListService;
    private final SWBICSServiceImpl swbicsService;
    private final DtoServiceImpl dtoService;
    private final UserServiceImpl userService;
    private final JwtService jwtService;

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


            for (BICDirectoryEntry bde: ed807.getBicDirectoryEntries()){
                List<SWBICS> swbics = bde.getSwbicsList();
                Optional.ofNullable(swbics).ifPresent(s->s.parallelStream().forEach(swbics1 -> swbics1.setBicDirectoryEntry(bde)));

                List<RestrictionList> restrictionLists = bde.getParticipantInfo().getRestrictionLists();
                Optional.ofNullable(restrictionLists).ifPresent(rl->rl.parallelStream()
                        .forEach(restrictionList -> restrictionList.setParticipantInfo(bde.getParticipantInfo())));

                List<Accounts> accounts = bde.getAccounts();
                Optional.ofNullable(accounts).ifPresent(a->a.parallelStream().forEach(accounts1 -> {
                    Optional.ofNullable(accounts1.getAccountRestrictionLists()).ifPresent(arl->arl.parallelStream()
                            .forEach(accountRestrictionList -> accountRestrictionList.setAccount(accounts1)));
                    accounts1.setBicDirectoryEntry(bde);
                }));

                bde.setEd807(ed807);
            }

            ed807.setTitle(title);
            ed807.setFileName(file.getOriginalFilename());
            ed807.setUploadDate(LocalDate.now());
            ed807Service.save(ed807);

            User user = userService.getUser(username).get();
            Set<ED807> ed807Set = user.getEd807s() != null ? user.getEd807s() : new HashSet<>();
            ed807Set.add(ed807);
            user.setEd807s(ed807Set);
            userService.update(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
