package com.ocode.cbrf.service;

import com.ocode.cbrf.model.Accounts;

public interface AccountsService {
    Accounts save(Accounts accounts);
    void delete(Long id);
}
