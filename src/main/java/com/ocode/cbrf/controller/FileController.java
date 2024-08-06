package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.components.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.*;
import com.ocode.cbrf.model.*;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//http://localhost:8080/api/xml_file/upload?fileName=20230518_ED807_full.xml
//http://localhost:8080/api/xml_file/upload?fileName=20220630_ED807_full.xml
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

    @Autowired
    public FileController(FileServiceImpl fileService, ED807ServiceImpl ed807Service,
                          BICDirectoryEntryServiceImpl bicDirectoryEntryService,
                          ParticipantInfoServiceImpl participantInfoService, AccountsServiceImpl accountsService,
                          RestrictionListServiceImpl restrictionListService,
                          AccountRestrictionListServiceImpl accountRestrictionListService,
                          SWBICSServiceImpl swbicsService, DtoServiceImpl dtoService,UserServiceImpl userService) {
        this.fileService = fileService;
        this.ed807Service = ed807Service;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.participantInfoService = participantInfoService;
        this.accountsService = accountsService;
        this.restrictionListService = restrictionListService;
        this.accountRestrictionListService = accountRestrictionListService;
        this.swbicsService = swbicsService;
        this.dtoService = dtoService;
        this.userService= userService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file){
        try{
            ED807Dto ed807Dto = fileService.unmarshalXml(fileService.convertMultipartFileToFile(file));
            ////////
            ED807 ed807 = dtoService.toEntities(ed807Dto);
            List<BICDirectoryEntry> bicDirectoryEntries = ed807.getBicDirectoryEntries();
            if(bicDirectoryEntries != null)
            {
                for(BICDirectoryEntry bde: bicDirectoryEntries)
                {
                    if(bde.getSwbicsList() != null){
                        for(SWBICS s: bde.getSwbicsList())
                            swbicsService.save(s);
                    }
                    ParticipantInfo participantInfo =bde.getParticipantInfo();
                    if(participantInfo.getRestrictionLists() != null){
                        for(RestrictionList rl: participantInfo.getRestrictionLists())
                            restrictionListService.save(rl);
                    }
                    participantInfoService.save(participantInfo);
                    List<Accounts> accountsList = bde.getAccounts();
                    if(accountsList != null){
                        for(Accounts a: accountsList){
                            if(a.getAccountRestrictionLists() != null){
                                for(AccountRestrictionList arl: a.getAccountRestrictionLists())
                                    accountRestrictionListService.save(arl);
                            }
                            accountsService.save(a);
                        }
                    }
                    bde.setDeleted(false);
                    bicDirectoryEntryService.save(bde);
                }
            }
            ed807.setDeleted(false);
            ed807Service.save(ed807);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = "";
            if(authentication != null && authentication.getPrincipal() instanceof CbrfUserDetails userDetails){
                currentUsername = userDetails.getUsername();
            }

            User user = userService.getUser(currentUsername).get();
            Set<ED807> ed807Set = user.getEd807s()!=null ? user.getEd807s() : new HashSet<>();
            ed807Set.add(ed807);
            user.setEd807s(ed807Set);
            userService.update(user);

            return new ResponseEntity<>(ed807.getBicDirectoryEntries().get(1).getBic().toString(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
