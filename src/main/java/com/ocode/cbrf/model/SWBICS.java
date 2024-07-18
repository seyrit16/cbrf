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

    @ManyToMany(mappedBy = "swbicsList")
    private List<BICDirectoryEntry> bicDirectoryEntries;
}
