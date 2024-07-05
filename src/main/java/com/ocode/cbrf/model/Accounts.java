package com.ocode.cbrf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "accounts")
    private List<BICDirectoryEntry> bicDirectoryEntries;
}
