package com.ocode.cbrf.controller;

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
    public ResponseEntity<String> update(@RequestParam("accRstrListId") Long accRstrListId, @RequestBody Map<String, String> data){
        try{
            accountRestrictionListService.update(accRstrListId, data);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
