package com.ocode.cbrf.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "swbics")
public class SWBICS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "swbics")
    private Set<BICDirectoryEntry> BICDirectoryEntries;
}
