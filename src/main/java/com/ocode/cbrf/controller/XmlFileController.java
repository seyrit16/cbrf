package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.*;
import com.ocode.cbrf.model.*;
import com.ocode.cbrf.service.*;
import com.ocode.cbrf.service.impl.XmlFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//http://localhost:8080/api/xml_file/upload?fileName=20230518_ED807_full.xml
//http://localhost:8080/api/xml_file/upload?fileName=20220630_ED807_full.xml
@Controller
@RequestMapping("/api/xml_file")
public class XmlFileController {
    @Autowired
    XmlFileServiceImpl xmlFileService;

    @Autowired
    ED807Service ed807Service;
    @Autowired
    BICDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    ParticipantInfoService participantInfoService;
    @Autowired
    AccountsService accountsService;
    @Autowired
    RestrictionListService restrictionListService;
    @Autowired
    AccountRestrictionListService accountRestrictionListService;
    @Autowired
    SWBICSService swbicsService;
    @Autowired
    DtoService dtoService;

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
                    bicDirectoryEntryService.save(bde);
                }
            }
            ed807Service.save(ed807);

            return new ResponseEntity<>(ed807.getBicDirectoryEntries().get(1).getBic().toString(),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
