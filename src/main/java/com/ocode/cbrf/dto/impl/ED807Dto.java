package com.ocode.cbrf.dto.impl;

import com.ocode.cbrf.adapter.LocalDateAdapter;
import com.ocode.cbrf.adapter.OffsetDateTimeAdapter;
import com.ocode.cbrf.dto.Dto;
import com.ocode.cbrf.invariants.CreationReason;
import com.ocode.cbrf.invariants.InfoTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
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
@Schema(description = "ED807")
public class ED807Dto implements Dto {
    @Positive
    @XmlTransient
    @Schema(description = "ED807 id")
    private Long id;

    @Transient
    @Schema(description = "Title", example = "My ED807 name")
    private String title;

    @Transient
    @Schema(description = "File name", example = "20220630_ED807_full.xml")
    private String fileName;

    @Transient
    @Schema(description = "Upload date")
    private LocalDate uploadDate;

    @NotNull
    @XmlAttribute(name = "EDNo")
    @Schema(description = "ED number", example = "707497754")
    private Integer number;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "EDDate")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @Schema(description = "ED date")
    private LocalDate date;

    @NotNull
    @XmlAttribute(name = "EDAuthor")
    @Schema(description = "ED author", example = "4583001999")
    private Long author;

    @XmlAttribute(name = "EDReceiver")
    @Schema(description = "ED receiver", defaultValue = "null")
    private Long receiver;

    @NotNull
    @XmlAttribute(name = "CreationReason")
    @Schema(description = "Creation reason", allowableValues = {"RQST", "CIBD", "FCBD"})
    private CreationReason creationReason;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "YYYY-MM-DD'T'hh:mm:ssZ")
    @XmlAttribute(name = "CreationDateTime")
    @XmlJavaTypeAdapter(OffsetDateTimeAdapter.class)
    @Schema(description = "Creation date time")
    private OffsetDateTime creationDateTime;

    @NotNull
    @XmlAttribute(name = "InfoTypeCode")
    @Schema(description = "Info type code", allowableValues = {"FIRR", "SIRR"})
    private InfoTypeCode infoTypeCode;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @XmlAttribute(name = "BusinessDay")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Schema(description = "Business day")
    private LocalDate businessDay;

    @XmlAttribute(name = "DirectoryVersion")
    @Schema(description = "Directory version", example = "1")
    private Integer directoryVersion;

    @XmlElement(name = "BICDirectoryEntry", namespace = "urn:cbr-ru:ed:v2.0")
    private List<BICDirectoryEntryDto> bicDirectoryEntries;

    @Schema(description = "Is deleted", allowableValues = {"true", "false"})
    private Boolean deleted;
}
