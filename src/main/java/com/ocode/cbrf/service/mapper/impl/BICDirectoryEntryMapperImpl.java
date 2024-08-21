package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.AccountsDto;
import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.impl.SWBICSDto;
import com.ocode.cbrf.model.Accounts;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.model.SWBICS;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BICDirectoryEntryMapperImpl extends AbstractCbrfMapper<BICDirectoryEntry, BICDirectoryEntryDto> {
    private final ParticipantInfoMapperImpl participantInfoMapper;
    private final AccountsMapperImpl accountsMapper;
    private final SWBICSMapperImpl swbicsMapper;

    public BICDirectoryEntryMapperImpl(ParticipantInfoMapperImpl participantInfoMapper,
                                       AccountsMapperImpl accountsMapper,
                                       SWBICSMapperImpl swbicsMapper) {
        super(BICDirectoryEntry.class, BICDirectoryEntryDto.class);
        this.participantInfoMapper = participantInfoMapper;
        this.accountsMapper = accountsMapper;
        this.swbicsMapper = swbicsMapper;
    }

    @Override
    public BICDirectoryEntryDto toDto(BICDirectoryEntry entity) {
        if (entity == null) {
            return null;
        }

        BICDirectoryEntryDto dto = super.toDto(entity);

        dto.setParticipantInfo(participantInfoMapper.toDto(entity.getParticipantInfo()));

        if (entity.getAccounts() != null) {
            List<AccountsDto> accountsDtos = entity.getAccounts()
                    .stream()
                    .map(accountsMapper::toDto)
                    .collect(Collectors.toList());
            dto.setAccounts(accountsDtos);
        }

        if (entity.getSwbicsList() != null) {
            List<SWBICSDto> swbicsDtos = entity.getSwbicsList()
                    .stream()
                    .map(swbicsMapper::toDto)
                    .collect(Collectors.toList());
            dto.setSwbicsList(swbicsDtos);
        }

        return dto;
    }

    @Override
    public BICDirectoryEntry toEntity(BICDirectoryEntryDto dto) {
        if (dto == null) {
            return null;
        }

        BICDirectoryEntry entity = super.toEntity(dto);

        entity.setParticipantInfo(participantInfoMapper.toEntity(dto.getParticipantInfo()));

        if (dto.getAccounts() != null) {
            List<Accounts> accountsList = dto.getAccounts()
                    .stream()
                    .map(accountsMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setAccounts(accountsList);
        }

        if (dto.getSwbicsList() != null) {
            List<SWBICS> swbicsLists= dto.getSwbicsList()
                    .stream()
                    .map(swbicsMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setSwbicsList(swbicsLists);
        }

        return entity;
    }
}
