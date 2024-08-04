package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.AccountStatus;
import com.ocode.cbrf.invariants.RegulationAccountType;
import com.ocode.cbrf.model.Accounts;
import com.ocode.cbrf.repository.AccountsRepository;
import com.ocode.cbrf.service.AccountsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    @Transactional
    public Accounts save(Accounts accounts) {
        return accountsRepository.save(accounts);
    }

    @Override
    @Transactional
    public void update(Long id, Map<String, String> data) {
        Accounts accounts = getById(id).orElse(null);
        if(accounts == null)
            throw new NullPointerException("accounts is null");

        Optional.ofNullable(data.get("account")).ifPresent(accounts::setAccount);
        Optional.ofNullable(data.get("regulationAccountType"))
                .map(RegulationAccountType::valueOf)
                .ifPresent(accounts::setRegulationAccountType);
        Optional.ofNullable(data.get("controlKey")).ifPresent(accounts::setControlKey);
        Optional.ofNullable(data.get("accountCBRBIC")).map(Integer::parseInt).ifPresent(accounts::setAccountCBRBIC);
        Optional.ofNullable(data.get("dateIn"))
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ifPresent(accounts::setDateIn);
        Optional.ofNullable(data.get("dateOut"))
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ifPresent(accounts::setDateOut);
        Optional.ofNullable(data.get("accountStatus")).map(AccountStatus::valueOf).ifPresent(accounts::setAccountStatus);

        accountsRepository.save(accounts);
    }

    @Override
    public Optional<Accounts> getById(Long id) {
        return accountsRepository.findById(id);
    }
}
