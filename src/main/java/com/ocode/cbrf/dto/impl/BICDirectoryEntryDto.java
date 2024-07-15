package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @XmlAttribute(name = "Accounts")
    private List<AccountsDto> accounts;

    @XmlAttribute(name = "SWBICS")
    private List<SWBICSDto> swbicsList;
}
