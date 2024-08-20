package com.ocode.cbrf.model;

import com.ocode.cbrf.invariants.CreationReason;
import com.ocode.cbrf.invariants.InfoTypeCode;
import com.ocode.cbrf.model.user.User;
import jakarta.persistence.*;
import lombok.*;
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
    @PrePersist
    public void prePersist() {
        if (deleted == null) {
            deleted = false;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

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

    @OneToMany(mappedBy = "ed807",cascade = CascadeType.PERSIST)
    private List<BICDirectoryEntry> bicDirectoryEntries;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ed807s")
    private Set<User> users;

    @Column(name = "deleted")
    private Boolean deleted;

    public void addBICDirectoryEntry(BICDirectoryEntry bicDirectoryEntry){
        bicDirectoryEntries.add(bicDirectoryEntry);
        bicDirectoryEntry.setEd807(this);
    }
}
