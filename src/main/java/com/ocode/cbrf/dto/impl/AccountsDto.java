package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.AccountStatus;
import com.ocode.cbrf.invariants.RegulationAccountType;
import com.ocode.cbrf.model.AccountRestrictionList;
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
public class AccountsDto implements Dto {
    @Positive
    @XmlTransient
    private Long id;

    @NotNull
    @XmlAttribute(name = "Account")
    private String account;

    @NotNull
    @XmlAttribute(name = "RegulationAccountType")
    private RegulationAccountType regulationAccountType;

    @XmlAttribute(name = "CK")
    private String controlKey;

    @NotNull
    @XmlAttribute(name = "AccountCBRBIC")
    private Integer accountCBRBIC;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "DateIn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "DateOut")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @XmlAttribute(name = "AccountStatus")
    private AccountStatus accountStatus;

    @XmlElement(name = "AccRstrList", namespace = "urn:cbr-ru:ed:v2.0")
    private List<AccountsRestrictionListDto> accountRestrictionLists;
}
