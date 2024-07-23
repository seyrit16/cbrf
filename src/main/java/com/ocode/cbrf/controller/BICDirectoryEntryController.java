package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.mapper.BICDirectoryEntryMapper;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/api/bic_directory_entry/all?ed_id=2
@RestController
@RequestMapping("/api/bic_directory_entry/")
public class BICDirectoryEntryController {
    @Autowired
    private BICDirectoryEntryMapper bicDirectoryEntryMapper;
    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @GetMapping("/all")
    public List<BICDirectoryEntryDto> getAllByED807(@RequestParam("ed_id") Long edId){
        try{
            List<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getBICDirectoryEntriesByEd807_ID(edId);
            List<BICDirectoryEntryDto> bdeDto = new ArrayList<>();
            for(BICDirectoryEntry bde: bicDirectoryEntries)
                bdeDto.add(bicDirectoryEntryMapper.toDto(bde));

            for(BICDirectoryEntryDto bde: bdeDto)
                System.out.println(bde.getBic());

            if(bdeDto != null && !bdeDto.isEmpty())
                return bdeDto;

            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
