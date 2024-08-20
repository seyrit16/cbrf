package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.RestrictionListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rstrList")
public class RestrictionListController {
    private final RestrictionListServiceImpl restrictionListService;

    public RestrictionListController(RestrictionListServiceImpl restrictionListService) {
        this.restrictionListService = restrictionListService;
    }

    @PutMapping("/update")
    public ResultDTO<?> update(@RequestParam("rstrId") Long rstrId, @RequestBody Map<String, String> data){
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
