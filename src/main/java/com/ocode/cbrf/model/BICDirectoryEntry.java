package com.ocode.cbrf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bic_directory_entry")
public class BICDirectoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BIC", nullable = false)
    private Integer BIC;

    @ManyToOne
    @JoinColumn(name = "participant_info_id", nullable = false)
    private ParticipantInfo participantInfo;

    @ManyToMany
    @JoinTable(
            name = "bicDirectoryEntry_accounts",
            joinColumns = @JoinColumn(name = "bic_directory_entry_id"),
            inverseJoinColumns = @JoinColumn(name = "Accounts_id")
    )
    private Set<Accounts> accountsSet;

    @ManyToMany
    @JoinTable(
            name = "bicDirectoryEntry_swbics",
            joinColumns = @JoinColumn(name = "bic_directory_entry_id"),
            inverseJoinColumns = @JoinColumn(name = "swbics_id")
    )
    private Set<SWBICS> swbicsSet;
}
