package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.components.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.mapper.BICDirectoryEntryMapperImpl;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.service.impl.BICDirectoryEntryServiceImpl;
import com.ocode.cbrf.service.impl.UserServiceImpl;
import com.ocode.cbrf.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bic_directory_entry/")
public class BICDirectoryEntryController {
    private final BICDirectoryEntryMapperImpl bicDirectoryEntryMapper;
    private final BICDirectoryEntryServiceImpl bicDirectoryEntryService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    public BICDirectoryEntryController(BICDirectoryEntryMapperImpl bicDirectoryEntryMapper, BICDirectoryEntryServiceImpl bicDirectoryEntryService, JwtService jwtService, UserServiceImpl userService) {
        this.bicDirectoryEntryMapper = bicDirectoryEntryMapper;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

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

    @GetMapping("/search/by_id")
    public BICDirectoryEntryDto getById(@RequestParam("bicId") Long bicId){
        try {
            Optional<BICDirectoryEntry> optionalBICDirectoryEntry =
                    bicDirectoryEntryService.getById(bicId);

            return  optionalBICDirectoryEntry.map(bicDirectoryEntryMapper::toDto).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/search/by_bic")
    public BICDirectoryEntryDto getByBic(@RequestParam("edId") Long edId, @RequestParam("bic") Integer bic,
                                         @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try{
            CbrfUserDetails userDetails = (CbrfUserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Optional<BICDirectoryEntry> optionalBICDirectoryEntry =
                    bicDirectoryEntryService.getByBic(edId,bic, userDetails.isAdmin());
            return optionalBICDirectoryEntry.map(bicDirectoryEntry -> bicDirectoryEntryMapper.toDto(bicDirectoryEntry)).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/search/by_ed")
    public List<BICDirectoryEntryDto> getByED807(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestParam("edId") Long edId,
                                                 @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try{
            String username = jwtService.extractUserName(authorizationHeader.split(" ")[1]);
            User user= userService.getUser(username).get();
            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getByEd807_ID(edId,user.getRole().getRole().equals("ADMIN"), pageable);
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
            CbrfUserDetails userDetails = (CbrfUserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Page<BICDirectoryEntry> bicDirectoryEntries =
                    bicDirectoryEntryService.getByParticipantNameAndParticipantType(edId,piName,piType,userDetails.isAdmin(),pageable);
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
