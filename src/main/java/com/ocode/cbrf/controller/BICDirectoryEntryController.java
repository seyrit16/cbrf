package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.mapper.BICDirectoryEntryMapper;
import com.ocode.cbrf.invariants.ChangeType;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//http://localhost:8080/api/bic_directory_entry/search/by_ed?edId=1
//http://localhost:8080/api/bic_directory_entry/search/by_ed_piName_piType?edId=1&piName=УФК%20по%20Астраханской%20области&piType=52
@RestController
@RequestMapping("/api/bic_directory_entry/")
public class BICDirectoryEntryController {
    @Autowired
    private BICDirectoryEntryMapper bicDirectoryEntryMapper;
    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam("edId") Long edId, @RequestBody Map<String,String> data){
        try{
            int status = bicDirectoryEntryService.update(edId,data);
            return switch (status) {
                case (404) -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
                case (409) -> new ResponseEntity<>("A BICDirectoryEntry with such a bic already exists", HttpStatus.CONFLICT);
                case (500) -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                default -> new ResponseEntity<>(HttpStatus.OK);
            };

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<String> delete(@RequestParam(name = "bicId") Long bicId){
        try {
            bicDirectoryEntryService.delete(bicId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/by_ed")
    public List<BICDirectoryEntryDto> getByED807(@RequestParam("edId") Long edId,
                                                 @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try{
            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getByEd807_ID(edId, pageable);
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
                                                                             @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try{
            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getByParticipantNameAndParticipantType(edId,piName,piType,pageable);
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
