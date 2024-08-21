package com.ocode.cbrf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "swbics")
public class SWBICS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "swbic")
    private String swbic;

    @Column(name = "default_swbic")
    private Boolean defaultSWBIC;

    @ManyToOne
    @JoinColumn(name = "bic_directory_entry_id")
    private BICDirectoryEntry bicDirectoryEntry;
}
