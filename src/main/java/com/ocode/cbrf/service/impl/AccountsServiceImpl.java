package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.Accounts;
import com.ocode.cbrf.repository.AccountsRepository;
import com.ocode.cbrf.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public Accounts save(Accounts accounts) {
        return accountsRepository.save(accounts);
    }

    @Override
    public void delete(Long id) {

    }
}
