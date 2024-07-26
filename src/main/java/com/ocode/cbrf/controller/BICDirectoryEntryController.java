package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.mapper.BICDirectoryEntryMapper;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/api/bic_directory_entry/search/by_ed?edId=1
//http://localhost:8080/api/bic_directory_entry/search/by_ed_piName_piType?edId=1&piName=УФК%20по%20Астраханской%20области&piType=52
@RestController
@RequestMapping("/api/bic_directory_entry/")
public class BICDirectoryEntryController {
    @Autowired
    private BICDirectoryEntryMapper bicDirectoryEntryMapper;
    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @GetMapping("/search/by_ed")
    public List<BICDirectoryEntryDto> getByED807(@RequestParam("edId") Long edId,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "20") int size){
        try{
            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getByEd807_ID(edId, page, size);
            List<BICDirectoryEntryDto> bdeDto = new ArrayList<>();
            for(BICDirectoryEntry bde: bicDirectoryEntries)
                bdeDto.add(bicDirectoryEntryMapper.toDto(bde));

            if(bdeDto.isEmpty())
                return null;

            return bdeDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/search/by_ed_piName_piType")
    public List<BICDirectoryEntryDto> getByParticipantNameAndParticipantType(@RequestParam("edId")Long edId,
                                                                             @RequestParam(name = "piName", required = false) String piName,
                                                                             @RequestParam(name = "piType", required = false) String piType,
                                                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                                                             @RequestParam(name = "size", defaultValue = "20") int size){
        try{
            System.out.println(piName);
            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getByParticipantNameAndParticipantType(edId,piName,piType,page,size);
            List<BICDirectoryEntryDto> bdeDto = new ArrayList<>();
            for (BICDirectoryEntry bde: bicDirectoryEntries)
                bdeDto.add(bicDirectoryEntryMapper.toDto(bde));

            if(bdeDto.isEmpty())
                return null;

            return bdeDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
