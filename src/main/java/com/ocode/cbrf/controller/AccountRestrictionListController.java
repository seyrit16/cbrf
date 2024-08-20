package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.AccountRestrictionListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accRstrList")
public class AccountRestrictionListController {
    private final AccountRestrictionListServiceImpl accountRestrictionListService;

    public AccountRestrictionListController(AccountRestrictionListServiceImpl accountRestrictionListService) {
        this.accountRestrictionListService = accountRestrictionListService;
    }

    @PutMapping("/update")
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
