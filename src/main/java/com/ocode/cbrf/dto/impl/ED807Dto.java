package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.adapter.OffsetDateTimeAdapter;
import com.ocode.cbrf.dto.Dto;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "ED807", namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
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
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
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
