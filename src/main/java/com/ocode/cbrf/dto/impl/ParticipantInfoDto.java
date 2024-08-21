package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.ParticipantStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Participant info")
public class ParticipantInfoDto implements Dto {
    @Positive
    @XmlTransient
    @Schema(description = "Participant info id")
    private Long id;

    @NotNull
    @XmlAttribute(name = "NameP")
    @Schema(description = "Name", example = "ПЕТЕРБУРГСКИЙ ФИЛИАЛ АО ЮНИКРЕДИТ БАНКА")
    private String name;

    @XmlAttribute(name = "EnglName")
    @Schema(description = "English name", example = "Petersburg Branch of AO UniCredit Bank")
    private String englishName;

    @XmlAttribute(name = "RegN")
    @Schema(description = "Registry number", example = "1/1")
    private String regNumber;

    @XmlAttribute(name = "CntrCd")
    @Schema(description = "Country code", example = "RU")
    private String countryCode;

    @NotNull
    @XmlAttribute(name = "Rgn")
    @Schema(description = "Region", example = "40")
    private String region;

    @XmlAttribute(name = "Ind")
    @Schema(description = "Index", example = "191025")
    private Integer index;

    @XmlAttribute(name = "Tnp")
    @Schema(description = "Type NP", example = "г")
    private String typeNP;

    @XmlAttribute(name = "Nnp")
    @Schema(description = "Name NP", example = "Санкт-Петербург")
    private String nameNP;

    @XmlAttribute(name = "Adr")
    @Schema(description = "Address", example = "наб р.Фонтанки, 48/2")
    private String address;

    @XmlAttribute(name = "PrntBIC")
    @Schema(description = "Parent BIC", example = "044525545")
    private Integer parentBIC;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Schema(description = "Date in")
    private LocalDate dateIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Schema(description = "Date out")
    private LocalDate dateOut;

    @NotNull
    @XmlAttribute(name = "PtType")
    @Schema(description = "Participant type", example = "30")
    private String participantType;

    @NotNull
    @XmlAttribute(name = "Srvcs")
    @Schema(description = "Services", example = "3")
    private String services;

    @NotNull
    @XmlAttribute(name = "XchType")
    @Schema(description = "Exchange type", example = "1")
    private String exchangeType;

    @NotNull
    @XmlAttribute(name = "UID")
    @Schema(description = "UID", example = "4030858000")
    private Long UId;

    @XmlAttribute(name = "ParticipantStatus")
    @Schema(description = "Participant status", allowableValues = {"PSAC", "PSDL"})
    private ParticipantStatus participantStatus;

    @XmlElement(name = "RstrList", namespace = "urn:cbr-ru:ed:v2.0")
    private List<RestrictionListDto> restrictionLists;
}
