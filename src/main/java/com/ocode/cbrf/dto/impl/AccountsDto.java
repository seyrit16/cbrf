package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.AccountStatus;
import com.ocode.cbrf.invariants.RegulationAccountType;
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
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Accounts")
public class AccountsDto implements Dto {
    @Positive
    @XmlTransient
    @Schema(description = "Accounts id")
    private Long id;

    @NotNull
    @XmlAttribute(name = "Account")
    @Schema(description = "Account", example = "40116810400000010011")
    private String account;

    @NotNull
    @XmlAttribute(name = "RegulationAccountType")
    @Schema(description = "Regulation account type",
            allowableValues = {"CBRA", "CRSA", "BANA", "TRSA", "TRUA", "CLAC", "UTRA"})
    private RegulationAccountType regulationAccountType;

    @XmlAttribute(name = "CK")
    @Schema(description = "Control key", example = "99")
    private String controlKey;

    @NotNull
    @XmlAttribute(name = "AccountCBRBIC")
    @Schema(description = "Account CBR BIC", example = "041280002")
    private Integer accountCBRBIC;

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

    @XmlAttribute(name = "AccountStatus")
    @Schema(description = "Account status", allowableValues = {"ACAC", "ACDL"})
    private AccountStatus accountStatus;

    @XmlElement(name = "AccRstrList", namespace = "urn:cbr-ru:ed:v2.0")
    private List<AccountsRestrictionListDto> accountRestrictionLists;
}
