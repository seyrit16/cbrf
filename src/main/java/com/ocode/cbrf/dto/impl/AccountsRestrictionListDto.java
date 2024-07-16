package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.model.Accounts;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Positive;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountsRestrictionListDto {
    @Positive
    @XmlTransient
    private Long id;

    @XmlAttribute(name = "AccRstr")
    private String accountRestriction;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "AccRstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    @XmlAttribute(name = "SuccessorBIC")
    private Integer successorBIC;

    private List<AccountsDto> accountsList;
}
