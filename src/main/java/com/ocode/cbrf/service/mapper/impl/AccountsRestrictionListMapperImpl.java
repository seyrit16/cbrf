package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.AccountsRestrictionListDto;
import com.ocode.cbrf.model.AccountRestrictionList;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountsRestrictionListMapperImpl extends AbstractCbrfMapper<AccountRestrictionList, AccountsRestrictionListDto> {
    public AccountsRestrictionListMapperImpl() {
        super(AccountRestrictionList.class, AccountsRestrictionListDto.class);
    }
}
