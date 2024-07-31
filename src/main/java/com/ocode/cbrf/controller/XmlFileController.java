package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.*;
import com.ocode.cbrf.model.*;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.repository.UserRepository;
import com.ocode.cbrf.service.*;
import com.ocode.cbrf.service.impl.XmlFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//http://localhost:8080/api/xml_file/upload?fileName=20230518_ED807_full.xml
//http://localhost:8080/api/xml_file/upload?fileName=20220630_ED807_full.xml
@Controller
@RequestMapping("/api/xml_file")
public class XmlFileController {
    final XmlFileServiceImpl xmlFileService;

    final ED807Service ed807Service;
    final BICDirectoryEntryService bicDirectoryEntryService;
    final ParticipantInfoService participantInfoService;
    final AccountsService accountsService;
    final RestrictionListService restrictionListService;
    final AccountRestrictionListService accountRestrictionListService;
    final SWBICSService swbicsService;
    final DtoService dtoService;
    final UserRepository userRepository;

    @Autowired
    public XmlFileController(XmlFileServiceImpl xmlFileService, ED807Service ed807Service,
                             BICDirectoryEntryService bicDirectoryEntryService,
                             ParticipantInfoService participantInfoService, AccountsService accountsService,
                             RestrictionListService restrictionListService,
                             AccountRestrictionListService accountRestrictionListService, SWBICSService swbicsService, DtoService dtoService,
                             UserRepository userRepository) {
        this.xmlFileService = xmlFileService;
        this.ed807Service = ed807Service;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.participantInfoService = participantInfoService;
        this.accountsService = accountsService;
        this.restrictionListService = restrictionListService;
        this.accountRestrictionListService = accountRestrictionListService;
        this.swbicsService = swbicsService;
        this.dtoService = dtoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("fileName") String fileName){
        try{
            ED807Dto ed807Dto = xmlFileService.unmarshalXml(fileName);
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

            User user = userRepository.findUserByLogin(currentUsername).get();
            Set<ED807> ed807Set = user.getEd807s()!=null ? user.getEd807s() : new HashSet<>();
            ed807Set.add(ed807);
            user.setEd807s(ed807Set);
            userRepository.save(user);

            return new ResponseEntity<>(ed807.getBicDirectoryEntries().get(1).getBic().toString(),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
