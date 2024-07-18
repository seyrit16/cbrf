package com.ocode.cbrf.service;

import com.ocode.cbrf.model.AccountRestrictionList;

public interface AccountRestrictionListService {
    AccountRestrictionList save(AccountRestrictionList accountRestrictionList);
    void delete(Long id);
}
