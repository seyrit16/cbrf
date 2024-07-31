package com.ocode.cbrf.service;

import com.ocode.cbrf.model.AccountRestrictionList;

import java.util.Map;
import java.util.Optional;

public interface AccountRestrictionListService {
    AccountRestrictionList save(AccountRestrictionList accountRestrictionList);
    void update(Long id, Map<String,String> data);
    Optional<AccountRestrictionList> getById(Long id);
}
