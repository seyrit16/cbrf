package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.adapter.OffsetDateTimeAdapter;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ED807")
public class ED807Dto implements Dto {
    @Positive
    @XmlTransient
    private Long id;

    @NotNull
    @XmlAttribute(name = "EDNo")
    private Integer number;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "EDDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    @NotNull
    @XmlAttribute(name = "EDAuthor")
    private Long author;

    @XmlAttribute(name = "EDReceiver")
    private Long receiver;

    @NotNull
    @XmlAttribute(name = "CreationReason")
    private String creationReason;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "YYYY-MM-DD'T'hh:mm:ssZ")
    @XmlAttribute(name = "CreationDateTime")
    @XmlJavaTypeAdapter(OffsetDateTimeAdapter.class)
    private OffsetDateTime creationDateTime;

    @NotNull
    @XmlAttribute(name = "InfoTypeCode")
    private String infoTypeCode;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "BusinessDay")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate businessDay;

    @XmlAttribute(name = "DirectoryVersion")
    private Integer directoryVersion;

    @XmlElement(name = "BICDirectoryEntry")
    private List<BICDirectoryEntryDto> bicDirectoryEntries;
}
