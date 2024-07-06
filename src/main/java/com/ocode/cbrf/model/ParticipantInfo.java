package com.ocode.cbrf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "participant_info")
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "reg_number")
    private Integer regNumber;

    @Column(name = "country code")
    private String countryCode;

    @NonNull
    @Column(name = "region")
    private String region;

    @Column(name = "index")
    private Integer index;

    @Column(name = "type_np")
    private String typeNP;

    @Column(name = "name_np")
    private String nameNP;

    @Column(name = "address")
    private String address;

    @Column(name = "parent_name")
    private Integer parentName;

    @NonNull
    @Column(name = "date_in")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateIn;

    @Column(name = "date_out")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOut;

    @NonNull
    @Column(name = "participant_type")
    private String participantType;

    @NonNull
    @Column(name = "services")
    private String services;

    @NonNull
    @Column(name = "exchange_type")
    private String exchangeType;

    @NonNull
    @Column(name = "uid")
    private Long UId;

    @Column(name = "participant_status")
    private String participantStatus;

    @ManyToMany
    @JoinTable(
            name = "participantInfo_rstrList",
            joinColumns = @JoinColumn(name = "participant_info_id"),
            inverseJoinColumns = @JoinColumn(name = "rstr_list_id")
    )
    private List<RestrictionList> restrictionLists;

    @OneToMany(mappedBy = "participantInfo")
    private List<BICDirectoryEntry> bicDirectoryEntries;
}
