package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantInfoDto implements Dto {
    @Positive
    @XmlTransient
    private Long id;

    @NotNull
    @XmlAttribute(name = "NameP")
    private String name;

    @XmlAttribute(name = "EnglName")
    private String englishName;

    @XmlAttribute(name = "RegN")
    private Integer regNumber;

    @XmlAttribute(name = "CntrCd")
    private String countryCode;

    @NotNull
    @XmlAttribute(name = "Rgn")
    private String region;

    @XmlAttribute(name = "Ind")
    private Integer index;

    @XmlAttribute(name = "Tnp")
    private String typeNP;

    @XmlAttribute(name = "Nnp")
    private String nameNP;

    @XmlAttribute(name = "Adr")
    private String address;

    @XmlAttribute(name = "PrntBIC")
    private Integer parentBIC;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @NotNull
    @XmlAttribute(name = "PtType")
    private String participantType;

    @NotNull
    @XmlAttribute(name = "Srvcs")
    private String services;

    @NotNull
    @XmlAttribute(name = "XchType")
    private String exchangeType;

    @NotNull
    @XmlAttribute(name = "UID")
    private Long UId;

    @XmlAttribute(name = "ParticipantStatus")
    private String participantStatus;

    @XmlElement(name = "RstrList")
    private List<RestrictionListDto> restrictionLists;

    private List<BICDirectoryEntryDto> bicDirectoryEntries;
}
