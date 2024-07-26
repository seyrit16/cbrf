package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.mapper.BICDirectoryEntryMapper;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/api/bic_directory_entry/all?ed_id=1
@RestController
@RequestMapping("/api/bic_directory_entry/")
public class BICDirectoryEntryController {
    @Autowired
    private BICDirectoryEntryMapper bicDirectoryEntryMapper;
    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @GetMapping("/all_by_ed")
    public List<BICDirectoryEntryDto> getAllByED807(@RequestParam("edId") Long edId,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "20") int size){
        try{
            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getBICDirectoryEntriesByEd807_ID(edId, page, size);
            List<BICDirectoryEntryDto> bdeDto = new ArrayList<>();
            for(BICDirectoryEntry bde: bicDirectoryEntries)
                bdeDto.add(bicDirectoryEntryMapper.toDto(bde));

            if(bdeDto != null && !bdeDto.isEmpty())
                return bdeDto;

            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
