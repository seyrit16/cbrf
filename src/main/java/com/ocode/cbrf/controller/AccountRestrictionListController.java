package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.AccountRestrictionListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accRstrList")
@Tag(name = "Accounts restriction list controller")
public class AccountRestrictionListController {
    private final AccountRestrictionListServiceImpl accountRestrictionListService;

    @Autowired
    public AccountRestrictionListController(AccountRestrictionListServiceImpl accountRestrictionListService) {
        this.accountRestrictionListService = accountRestrictionListService;
    }

    @PutMapping("/update")
    @Operation(summary = "Update accounts restriction list by id")
    public ResultDTO<?> update(@RequestParam("accRstrListId") Long accRstrListId,
                               @RequestBody Map<String, String> data){
        try{
            accountRestrictionListService.update(accRstrListId, data);
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
