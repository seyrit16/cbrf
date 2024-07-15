package com.ocode.cbrf.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    /*
    * Since Hibernate 5 you don’t need and should not use @Temporal in new code.
    * It was for annotating Date and Calendar fields, but those classes are poorly
    * designed and long outdated Instead use classes from java.time, the modern
    * Java date and time API.
    */

    // кстати а почему он ни к кому не присоединяется? >_<

    @Temporal(TemporalType.DATE) //?
    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "successor_bic")
    private Integer successorBIC;
}
