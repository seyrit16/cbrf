package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.Rstr;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Restriction list")
public class RestrictionListDto implements Dto {
    @Positive
    @XmlTransient
    @Schema(description = "Restriction list id")
    private Long id;

    @NotNull
    @XmlAttribute(name = "Rstr")
    @Schema(description = "Restriction", allowableValues = {"URRS", "LWRS", "MRTR", "RSIP", "FPIP"})
    private Rstr restriction;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "RstrDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Schema(description = "Restriction date")
    private LocalDate date;
}
