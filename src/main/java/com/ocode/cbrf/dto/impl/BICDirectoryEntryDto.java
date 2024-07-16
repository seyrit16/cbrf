package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import jakarta.xml.bind.annotation.*;
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
@XmlAccessorType(XmlAccessType.FIELD)
public class BICDirectoryEntryDto implements Dto {
    @Positive
    @XmlTransient
    private Long id;

    @NotNull
    @XmlAttribute(name = "BIC")
    private Integer bic;

    @XmlAttribute(name = "ChangeType")
    private String changeType;

    @NotNull
    @XmlElement(name = "ParticipantInfo")
    private ParticipantInfoDto participantInfo;

    @XmlElement(name = "Accounts")
    private List<AccountsDto> accounts;

    @XmlElement(name = "SWBICS")
    private List<SWBICSDto> swbicsList;
}
