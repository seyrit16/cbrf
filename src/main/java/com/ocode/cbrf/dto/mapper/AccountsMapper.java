package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.AccountsDto;
import com.ocode.cbrf.model.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountsMapper {
    AccountsMapper INSTANCE = Mappers.getMapper(AccountsMapper.class);

    AccountsDto toDto(Accounts accounts);
    Accounts toEntity(AccountsDto accountsDto);
}
