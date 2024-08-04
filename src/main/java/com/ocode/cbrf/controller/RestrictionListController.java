package com.ocode.cbrf.controller;

import com.ocode.cbrf.service.RestrictionListService;
import com.ocode.cbrf.service.impl.RestrictionListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rstrList")
public class RestrictionListController {
    @Autowired
    RestrictionListServiceImpl restrictionListService;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam("rstrId") Long rstrId, @RequestBody Map<String, String> data){
        try{
            restrictionListService.update(rstrId, data);
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
