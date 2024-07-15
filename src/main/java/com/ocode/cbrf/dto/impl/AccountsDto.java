package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto implements Dto {
    @Positive
    private Long id;

    @NotNull
    private String account;

    @NotNull
    private String regulationAccountType;


    private String controlKey;

    @NotNull
    private Integer accountCBRBIC;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateOut;


    private String accountStatus;


    private List<BICDirectoryEntryDto> bicDirectoryEntries;
}
