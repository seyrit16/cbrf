package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.dto.Dto;
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
public class SWBICSDto implements Dto {
    @XmlTransient
    private Long id;

    @XmlAttribute(name = "SWBIC")
    private String swbic;

    @XmlAttribute(name = "DefaultSWBIC")
    private Boolean defaultSWBIC;
}
