package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.AccountsDto;
import com.ocode.cbrf.dto.impl.AccountsRestrictionListDto;
import com.ocode.cbrf.model.AccountRestrictionList;
import com.ocode.cbrf.model.Accounts;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountsMapperImpl extends AbstractCbrfMapper<Accounts, AccountsDto> {
    private final AccountsRestrictionListMapperImpl accountsRestrictionListMapper;

    public AccountsMapperImpl(AccountsRestrictionListMapperImpl accountsRestrictionListMapper) {
        super(Accounts.class, AccountsDto.class);
        this.accountsRestrictionListMapper = accountsRestrictionListMapper;
    }

    @Override
    public AccountsDto toDto(Accounts entity) {
        if (entity == null) {
            return null;
        }

        AccountsDto dto = super.toDto(entity);

        if (entity.getAccountRestrictionLists() != null) {
            List<AccountsRestrictionListDto> accountRestrictionListDtos = entity.getAccountRestrictionLists()
                    .stream()
                    .map(accountsRestrictionListMapper::toDto)
                    .collect(Collectors.toList());
            dto.setAccountRestrictionLists(accountRestrictionListDtos);
        }

        return dto;
    }

    @Override
    public Accounts toEntity(AccountsDto dto) {
        if (dto == null) {
            return null;
        }

        Accounts entity = super.toEntity(dto);

        if (dto.getAccountRestrictionLists() != null) {
            List<AccountRestrictionList> accountRestrictionLists = dto.getAccountRestrictionLists()
                    .stream()
                    .map(accountsRestrictionListMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setAccountRestrictionLists(accountRestrictionLists);
        }

        return entity;
    }
}
