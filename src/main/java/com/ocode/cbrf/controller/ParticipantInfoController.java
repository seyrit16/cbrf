package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.ParticipantInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pi")
public class ParticipantInfoController {
    private final ParticipantInfoServiceImpl participantInfoService;

    public ParticipantInfoController(ParticipantInfoServiceImpl participantInfoService) {
        this.participantInfoService = participantInfoService;
    }

    @PutMapping("/update")
    public ResultDTO<?> update(@RequestParam("piId") Long piId, @RequestBody Map<String, String> data){
        try{
            participantInfoService.update(piId,data);
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
