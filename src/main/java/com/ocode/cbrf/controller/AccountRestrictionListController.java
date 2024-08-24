package com.ocode.cbrf.controller;

import com.ocode.cbrf.service.impl.AccountRestrictionListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accRstrList")
@Tag(name = "Accounts restriction list controller")
public class AccountRestrictionListController {
    private final AccountRestrictionListServiceImpl accountRestrictionListService;

    @Autowired
    public AccountRestrictionListController(AccountRestrictionListServiceImpl accountRestrictionListService) {
        this.accountRestrictionListService = accountRestrictionListService;
    }

    @PutMapping("/update")
    @Operation(summary = "Update accounts restriction list by id")
    public void update(
            @Parameter(description = "Accounts restriction list id to update")
            @RequestParam("accRstrListId") Long accRstrListId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data to update",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Example",
                                    description = "json may not contain all fields",
                                    value = """
                                            {
                                            "accountRestriction": "string",
                                            "date": "string",
                                            "successorBic": "string"
                                            }
                                            """)))
            @RequestBody Map<String, String> data) {
        accountRestrictionListService.update(accRstrListId, data);
    }
}
