package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.AccountRestrictionList;
import com.ocode.cbrf.repository.AccountRestrictionListRepository;
import com.ocode.cbrf.service.AccountRestrictionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRestrictionListServiceImpl implements AccountRestrictionListService {
    @Autowired
    AccountRestrictionListRepository accountRestrictionListRepository;

    @Override
    public AccountRestrictionList save(AccountRestrictionList accountRestrictionList) {
        return accountRestrictionListRepository.save(accountRestrictionList);
    }

    @Override
    public void delete(Long id) {

    }
}
