package com.ocode.cbrf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bic_directory_entry")
public class BICDirectoryEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "bic")
    private Integer bic;

    @Column(name = "change_type")
    private String changeType;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "participant_info_id")
    private ParticipantInfo participantInfo;

    @ManyToMany
    @JoinTable(
            name = "bicDirectoryEntry_accounts",
            joinColumns = @JoinColumn(name = "bic_directory_entry_id"),
            inverseJoinColumns = @JoinColumn(name = "accounts_id")
    )
    private List<Accounts> accounts;

    @ManyToMany
    @JoinTable(
            name = "bicDirectoryEntry_swbics",
            joinColumns = @JoinColumn(name = "bic_directory_entry_id"),
            inverseJoinColumns = @JoinColumn(name = "swbics_id")
    )
    private List<SWBICS> swbicsList;

    @Column(name = "deleted")
    private Boolean deleted;
}
