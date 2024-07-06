package com.ocode.cbrf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ed807")
public class ED807 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "number")
    private Integer number;

    @NonNull
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NonNull
    @Column(name="author")
    private Long author;

    @Column(name = "receiver")
    private Long receiver;

    @NonNull
    @Column(name = "creation_reason")
    private String creationReason;

    @NonNull
    @Column(name = "creation_date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private OffsetDateTime creationDateTime;

    @NonNull
    @Column(name = "info_type_code")
    private String infoTypeCode;

    @Column(name = "business_day")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate businessDay;

    @Column(name = "directory_version")
    private Integer directoryVersion;

    @ManyToMany
    @JoinTable(
            name = "ed807_bicDirectoryEntry",
            joinColumns = @JoinColumn(name = "ed807_id"),
            inverseJoinColumns = @JoinColumn(name = "bic_directory_entry_id")
    )
    private List<BICDirectoryEntry> bicDirectoryEntries;
}
