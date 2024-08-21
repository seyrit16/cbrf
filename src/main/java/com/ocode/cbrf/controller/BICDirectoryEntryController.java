package com.ocode.cbrf.controller;

import com.ocode.cbrf.config.security.components.CbrfUserDetails;
import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.service.mapper.impl.BICDirectoryEntryMapperImpl;
import com.ocode.cbrf.exception.ConflictDataException;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.service.impl.BICDirectoryEntryServiceImpl;
import com.ocode.cbrf.service.impl.UserServiceImpl;
import com.ocode.cbrf.service.web.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bic_directory_entry/")
@Tag(name = "BIC directory entry controller")
public class BICDirectoryEntryController {
    private final BICDirectoryEntryMapperImpl bicDirectoryEntryMapper;
    private final BICDirectoryEntryServiceImpl bicDirectoryEntryService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    @Autowired
    public BICDirectoryEntryController(BICDirectoryEntryMapperImpl bicDirectoryEntryMapper, BICDirectoryEntryServiceImpl bicDirectoryEntryService, JwtService jwtService, UserServiceImpl userService) {
        this.bicDirectoryEntryMapper = bicDirectoryEntryMapper;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PutMapping("/update")
    @Operation(summary = "Update BIC directory entries by ED807 id")
    public ResultDTO<?> update(
            @Parameter(description = "ED807 id to BIC directory entry update")
            @RequestParam("edId") Long edId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(mediaType = "application/json"))
            @RequestBody Map<String,String> data){
        try{
            bicDirectoryEntryService.update(edId,data);
            return ResultDTO.EMPTY_OK_RESULT;
        }catch (ConflictDataException cdE){
            cdE.printStackTrace();
            return new ResultDTO<>("false",409L,null,"409",cdE.getMessage());
        }catch (NullPointerException nullE){
          nullE.printStackTrace();
          return ResultDTO.NOT_FOUND_RESULT;
        } catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete BIC directory entry by id")
    public ResultDTO<?> delete(
            @Parameter(description = "BIC directory entry id to delete")
            @RequestParam(name = "bicId") Long bicId){
        try {
            bicDirectoryEntryService.delete(bicId);
            return ResultDTO.EMPTY_OK_RESULT;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/by_id")
    @Operation(summary = "Get BIC directory entry by id")
    public ResultDTO<BICDirectoryEntryDto> getById(
            @Parameter(description = "BIC directory entry id to search")
            @RequestParam("bicId") Long bicId){
        try {
            Optional<BICDirectoryEntry> optionalBICDirectoryEntry =
                    bicDirectoryEntryService.getById(bicId);

            if(optionalBICDirectoryEntry.isPresent())
                return new ResultDTO<BICDirectoryEntryDto>("true",200L,
                        optionalBICDirectoryEntry.map(bicDirectoryEntryMapper::toDto).get(),null,null);
            else
                return ResultDTO.NOT_FOUND_RESULT;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/by_bic")
    @Operation(summary = "Get BIC directory entry by BIC id and ED807 id")
    public ResultDTO<BICDirectoryEntryDto> getByBic(
            @Parameter(description = "ED807 id to BIC directory entry search")
            @RequestParam("edId") Long edId,

            @Parameter(description = "BIC directory entry number to search")
            @RequestParam("bic") Integer bic,

            @Parameter(description = "Pagination")
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){
        try{
            CbrfUserDetails userDetails = (CbrfUserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Optional<BICDirectoryEntry> optionalBICDirectoryEntry =
                    bicDirectoryEntryService.getByBic(edId,bic, userDetails.isAdmin());

            if(optionalBICDirectoryEntry.isPresent())
                return new ResultDTO<BICDirectoryEntryDto>("true",200L,
                        optionalBICDirectoryEntry.map(bicDirectoryEntryMapper::toDto).get(),null,null);
            else
                return ResultDTO.NOT_FOUND_RESULT;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }

    @GetMapping("/search/by_ed")
    @Operation(summary = "Get all BIC directory entries by ED807 id")
    public ResultDTO<List<BICDirectoryEntryDto>> getByED807(
            @Parameter(description = "JWT token")
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,

            @Parameter(description = "ED807 id to BIC directory entry search")
            @RequestParam("edId") Long edId,

            @Parameter(description = "Pagination")
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
                return ResultDTO.NOT_FOUND_RESULT;

            ResultDTO<List<BICDirectoryEntryDto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(bdeDto);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.NOT_FOUND_RESULT;
        }
    }

    @GetMapping("/search/by_ed_piName_piType")
    @Operation(summary = "Get all BIC directory entries by participant info name and participant info type")
    public ResultDTO<List<BICDirectoryEntryDto>> getByParticipantNameAndParticipantType(
            @Parameter(description = "ED807 id to BIC directory entry search")
            @RequestParam("edId")Long edId,

            @Parameter(description = "Participant info name to BIC directory entry search")
            @RequestParam(name = "piName", required = false) String piName,

            @Parameter(description = "Participant info type to BIC directory entry search")
            @RequestParam(name = "piType", required = false) String piType,

            @Parameter(description = "Pagination")
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
                return ResultDTO.NOT_FOUND_RESULT;

            ResultDTO<List<BICDirectoryEntryDto>> resultDTO = ResultDTO.EMPTY_OK_RESULT;
            resultDTO.setData(bdeDto);
            return resultDTO;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }
}
