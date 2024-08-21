package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.ResultDTO;
import com.ocode.cbrf.service.impl.ParticipantInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pi")
@Tag(name = "Participant info controller")
public class ParticipantInfoController {
    private final ParticipantInfoServiceImpl participantInfoService;

    @Autowired
    public ParticipantInfoController(ParticipantInfoServiceImpl participantInfoService) {
        this.participantInfoService = participantInfoService;
    }

    @PutMapping("/update")
    @Operation(summary = "Update participant info by id")
    public ResultDTO<?> update(
            @Parameter(description = "ParticipantInfo id to update")
            @RequestParam("piId") Long piId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(mediaType = "application/json"))
            @RequestBody Map<String, String> data){
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
