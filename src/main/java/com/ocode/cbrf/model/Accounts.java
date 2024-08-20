package com.ocode.cbrf.model;

import com.ocode.cbrf.invariants.AccountStatus;
import com.ocode.cbrf.invariants.RegulationAccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "account")
    private String account;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "regulation_account_type")
    private RegulationAccountType regulationAccountType;

    @Column(name = "control_key")
    private String controlKey;

    @NonNull
    @Column(name = "account_cbr_bic")
    private Integer accountCBRBIC;

    @NonNull
    @Column(name = "date_in")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateIn;

    @Column(name = "date_out")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateOut;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @ManyToOne
    @JoinColumn(name = "bic_directory_entry_id")
    private BICDirectoryEntry bicDirectoryEntry;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<AccountRestrictionList> accountRestrictionLists;
}
