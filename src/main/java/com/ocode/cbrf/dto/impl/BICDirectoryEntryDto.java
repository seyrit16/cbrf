package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.ChangeType;
import jakarta.xml.bind.annotation.*;
import lombok.*;

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
    private ChangeType changeType;

    @NotNull
    @XmlElement(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
    private ParticipantInfoDto participantInfo;

    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    private List<AccountsDto> accounts;

    @XmlElement(name = "SWBICS", namespace = "urn:cbr-ru:ed:v2.0")
    private List<SWBICSDto> swbicsList;

    private Boolean deleted;
}
