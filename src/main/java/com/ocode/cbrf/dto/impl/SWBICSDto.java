package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "SWBIC")
public class SWBICSDto implements Dto {
    @XmlTransient
    @Schema(description = "SWBIC id")
    private Long id;

    @XmlAttribute(name = "SWBIC")
    @Schema(description = "SWBIC", example = "INEARUMMXXX")
    private String swbic;

    @XmlAttribute(name = "DefaultSWBIC")
    @Schema(description = "Default SWBIC", example = "1", defaultValue = "null")
    private Boolean defaultSWBIC;
}
