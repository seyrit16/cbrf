package com.ocode.cbrf.model;

import com.ocode.cbrf.invariants.ChangeType;
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
    @PrePersist
    public void prePersist() {
        if (deleted == null) {
            deleted = false;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "bic")
    private Integer bic;

    @Enumerated(EnumType.STRING)
    @Column(name = "change_type")
    private ChangeType changeType;

    @NonNull
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "participant_info_id")
    private ParticipantInfo participantInfo;

    @ManyToOne
    @JoinColumn(name = "ed807_id")
    private ED807 ed807;

    @OneToMany(mappedBy = "bicDirectoryEntry", cascade = CascadeType.PERSIST)
    private List<Accounts> accounts;

    @OneToMany(mappedBy = "bicDirectoryEntry", cascade = CascadeType.PERSIST)
    private List<SWBICS> swbicsList;

    @Column(name = "deleted")
    private Boolean deleted;
}
