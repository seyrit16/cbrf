package com.ocode.cbrf.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account_restriction_list")
public class AccountRestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_restriction")
    private String accountRestriction;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "successor_bic")
    private Integer successorBIC;
}
