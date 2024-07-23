package com.ocode.cbrf.model;


import com.ocode.cbrf.invariants.AccRstr;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_restriction_list")
public class AccountRestrictionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_restriction")
    private AccRstr accountRestriction;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "successor_bic")
    private Integer successorBIC;

    @ManyToMany(mappedBy = "accountRestrictionLists")
    private List<Accounts> accountsList;
}
