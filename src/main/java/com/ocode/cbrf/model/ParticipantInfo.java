package com.ocode.cbrf.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "participant_info")
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "participantInfo")
    private Set<BICDirectoryEntry> BICDirectoryEntries;
}
