package com.ocode.cbrf.controller;

import com.ocode.cbrf.service.impl.ParticipantInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pi")
public class ParticipantInfoController {
    @Autowired
    ParticipantInfoServiceImpl participantInfoService;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam("piId") Long piId, @RequestBody Map<String, String> data){
        try{
            participantInfoService.update(piId,data);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException nullE){
            nullE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
