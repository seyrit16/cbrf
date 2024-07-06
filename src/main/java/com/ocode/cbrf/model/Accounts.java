package com.ocode.cbrf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
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

    @NonNull
    @Column(name = "account")
    private String account;

    @NonNull
    @Column(name = "regulation_account_type")
    private String regulationAccountType;

    @Column(name = "control_key")
    private String controlKey;

    @NonNull
    @Column(name = "account_cbr_bic")
    private Integer accountCBRBIC;

    @NonNull
    @Column(name = "date_in")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateIn;

    @Column(name = "date_out")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOut;

    @Column(name = "account_status")
    private String accountStatus;

    @ManyToMany(mappedBy = "accounts")
    private List<BICDirectoryEntry> bicDirectoryEntries;
}
