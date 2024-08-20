package com.ocode.cbrf.controller;

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
    public ResponseEntity<String> update(@RequestParam("acId") long acId, @RequestBody Map<String,String> data){
        try{
            accountsService.update(acId, data);
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
