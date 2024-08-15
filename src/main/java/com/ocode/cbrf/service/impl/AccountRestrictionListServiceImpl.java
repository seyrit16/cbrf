package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.AccRstr;
import com.ocode.cbrf.model.AccountRestrictionList;
import com.ocode.cbrf.repository.AccountRestrictionListRepository;
import com.ocode.cbrf.service.AccountRestrictionListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountRestrictionListServiceImpl implements AccountRestrictionListService {
    @Autowired
    AccountRestrictionListRepository accountRestrictionListRepository;

    @Override
    @Transactional
    public AccountRestrictionList save(AccountRestrictionList accountRestrictionList) {
        return accountRestrictionListRepository.save(accountRestrictionList);
    }

    @Override
    @Transactional
    public void update(Long id, Map<String, String> data) {
        AccountRestrictionList accountRestrictionList = getById(id).orElse(null);
        if(accountRestrictionList == null) throw new NullPointerException("accountRestrictionList is null");

        Optional.ofNullable(data.get("accountRestriction")).map(AccRstr::valueOf).ifPresent(accountRestrictionList::setAccountRestriction);
        Optional.ofNullable(data.get("date"))
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ifPresent(accountRestrictionList::setDate);
        Optional.ofNullable(data.get("successorBIC")).map(Integer::parseInt).ifPresent(accountRestrictionList::setSuccessorBIC);

        accountRestrictionListRepository.save(accountRestrictionList);
    }

    @Override
    public Optional<AccountRestrictionList> getById(Long id) {
        return accountRestrictionListRepository.findById(id);
    }
}
