package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SWBICSDto implements Dto {

    private Long id;


    private String swbic;


    private Boolean defaultSWBIC;


    private List<BICDirectoryEntryDto> bicDirectoryEntries;
}
