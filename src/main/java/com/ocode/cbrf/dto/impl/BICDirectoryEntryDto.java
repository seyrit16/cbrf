package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BICDirectoryEntryDto implements Dto {
    @Positive
    private Long id;


    private String changeType;

    @NotNull
    private ParticipantInfoDto participantInfo;


    private List<AccountsDto> accounts;


    private List<SWBICSDto> swbicsList;
}
