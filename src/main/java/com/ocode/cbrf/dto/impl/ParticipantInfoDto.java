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
public class ParticipantInfoDto implements Dto {
    @Positive
    private Long id;

    @NotNull
    private String name;


    private String englishName;


    private Integer regNumber;


    private String countryCode;

    @NotNull
    private String region;


    private Integer index;


    private String typeNP;


    private String nameNP;


    private String address;


    private Integer parentBIC;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateOut;

    @NotNull
    private String participantType;

    @NotNull
    private String services;

    @NotNull
    private String exchangeType;

    @NotNull
    private Long UId;


    private String participantStatus;


    private List<RestrictionListDto> restrictionLists;


    private List<BICDirectoryEntryDto> bicDirectoryEntries;
}
