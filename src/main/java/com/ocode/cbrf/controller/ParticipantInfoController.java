package com.ocode.cbrf.controller;

import com.ocode.cbrf.service.impl.ParticipantInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
    public void update(
            @Parameter(description = "ParticipantInfo id to update")
            @RequestParam("piId") Long piId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example",
                                    description = "json may not contain all fields",
                                    value = """
                                            {
                                            "name": "string",
                                            "englishName": "string",
                                            "regNumber": "string",
                                            "countryCode": "string",
                                            "region": "string",
                                            "index": "string",
                                            "typeNP": "string",
                                            "nameNP": "string",
                                            "address": "string",
                                            "parentBIC": "string",
                                            "dateIn": "string",
                                            "dateOut": "string",
                                            "participantType": "string",
                                            "services": "string",
                                            "exchangeType": "string",
                                            "UId": "string",
                                            "participantStatus": "string"
                                            }
                                            """)))
            @RequestBody Map<String, String> data) {
        participantInfoService.update(piId, data);
    }
}
