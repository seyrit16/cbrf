package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
    private final AccountsServiceImpl accountsService;

    public AccountsController(AccountsServiceImpl accountsService) {
        this.accountsService = accountsService;
    }

    @PutMapping("/update")
    public ResultDTO<?> update(@RequestParam("acId") long acId, @RequestBody Map<String,String> data){
        try{
            accountsService.update(acId, data);
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
