package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.AccountsRestrictionListDto;
import com.ocode.cbrf.model.AccountRestrictionList;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountsRestrictionListMapper {
    AccountsRestrictionListMapper INSTANCE = Mappers.getMapper(AccountsRestrictionListMapper.class);

    AccountsRestrictionListDto toDto(AccountRestrictionList accountRestrictionList);
    AccountRestrictionList toEntity(AccountsRestrictionListDto accountsRestrictionListDto);
}
