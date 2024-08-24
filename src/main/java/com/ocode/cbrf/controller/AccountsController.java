package com.ocode.cbrf.controller;

import com.ocode.cbrf.service.impl.AccountsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts controller")
public class AccountsController {
    private final AccountsServiceImpl accountsService;

    @Autowired
    public AccountsController(AccountsServiceImpl accountsService) {
        this.accountsService = accountsService;
    }

    @PutMapping("/update")
    @Operation(summary = "Update accounts by id")
    public void update(
            @Parameter(description = "Accounts id to update")
            @RequestParam("acId") long acId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example",
                                    description = "json may not contain all fields",
                                    value = """
                                            {
                                            "account": "string",
                                            "regulationAccountType": "string",
                                            "controlKey": "string",
                                            "accountCBRBIC": "string",
                                            "dateIn": "string",
                                            "dateOut": "string",
                                            "accountStatus": "string"
                                            }
                                            """)))
            @RequestBody Map<String,String> data){
            accountsService.update(acId, data);
    }
}
