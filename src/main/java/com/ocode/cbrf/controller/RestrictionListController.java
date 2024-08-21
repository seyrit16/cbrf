package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.RestrictionListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rstrList")
@Tag(name = "Restriction list controller")
public class RestrictionListController {
    private final RestrictionListServiceImpl restrictionListService;

    @Autowired
    public RestrictionListController(RestrictionListServiceImpl restrictionListService) {
        this.restrictionListService = restrictionListService;
    }

    @PutMapping("/update")
    @Operation(summary = "Update restriction list by id")
    public ResultDTO<?> update(
            @Parameter(description = "Restriction list id")
            @RequestParam("rstrId") Long rstrId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(mediaType = "application/json"))
            @RequestBody Map<String, String> data){
        try{
            restrictionListService.update(rstrId, data);
            return ResultDTO.EMPTY_OK_RESULT;
        }catch (NullPointerException nullE){
            nullE.printStackTrace();
            return ResultDTO.NOT_FOUND_RESULT;
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.INTERNAL_SERVER_RESULT;
        }
    }
}
