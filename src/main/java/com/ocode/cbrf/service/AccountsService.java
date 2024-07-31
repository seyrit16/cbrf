package com.ocode.cbrf.service;

import com.ocode.cbrf.model.Accounts;

import java.util.Map;
import java.util.Optional;

public interface AccountsService {
    Accounts save(Accounts accounts);
    void update(Long id, Map<String,String> data);
    Optional<Accounts> getById(Long id);
}
