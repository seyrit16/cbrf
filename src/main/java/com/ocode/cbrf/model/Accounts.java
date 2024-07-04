package com.ocode.cbrf.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(mappedBy = "accounts")
    private Set<BICDirectoryEntry> BICDirectoryEntries;
}
