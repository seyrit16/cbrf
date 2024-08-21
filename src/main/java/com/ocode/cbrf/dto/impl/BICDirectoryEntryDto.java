package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.ChangeType;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "BIC directory entry")
public class BICDirectoryEntryDto implements Dto {
    @Positive
    @XmlTransient
    @Schema(description = "BIC directory entry id")
    private Long id;

    @NotNull
    @XmlAttribute(name = "BIC")
    @Schema(description = "BIC", example = "041280103")
    private Integer bic;

    @XmlAttribute(name = "ChangeType")
    @Schema(description = "Change type", allowableValues = {"ADDD", "CHGD", "DLTD"})
    private ChangeType changeType;

    @NotNull
    @XmlElement(name = "ParticipantInfo", namespace = "urn:cbr-ru:ed:v2.0")
    private ParticipantInfoDto participantInfo;

    @XmlElement(name = "Accounts", namespace = "urn:cbr-ru:ed:v2.0")
    private List<AccountsDto> accounts;

    @XmlElement(name = "SWBICS", namespace = "urn:cbr-ru:ed:v2.0")
    private List<SWBICSDto> swbicsList;

    @Schema(description = "Is deleted", allowableValues = {"true", "false"})
    private Boolean deleted;
}
