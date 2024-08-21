package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.invariants.AccRstr;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Positive;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Accounts restriction list")
public class AccountsRestrictionListDto {
    @Positive
    @XmlTransient
    @Schema(description = "Accounts restriction list id")
    private Long id;

    @XmlAttribute(name = "AccRstr")
    @Schema(description = "Accounts restriction", allowableValues = {"LMRS", "URRS", "CLRS", "FPRS", "SDRS"})
    private AccRstr accountRestriction;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "AccRstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Schema(description = "Accounts restriction date")
    private LocalDate date;

    @XmlAttribute(name = "SuccessorBIC")
    @Schema(description = "Successor BIC", defaultValue = "null")
    private Integer successorBIC;
}
