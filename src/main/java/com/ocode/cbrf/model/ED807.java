package com.ocode.cbrf.model;

import com.ocode.cbrf.invariants.CreationReason;
import com.ocode.cbrf.invariants.InfoTypeCode;
import com.ocode.cbrf.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NonNull
    @Column(name="author")
    private Long author;

    @Column(name = "receiver")
    private Long receiver;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "creation_reason")
    private CreationReason creationReason;

    @NonNull
    @Column(name = "creation_date_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "YYYY-MM-DD'T'hh:mm:ssZ")
    private OffsetDateTime creationDateTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "info_type_code")
    private InfoTypeCode infoTypeCode;

    @NonNull
    @Column(name = "business_day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ed807s")
    private Set<User> users;
}
