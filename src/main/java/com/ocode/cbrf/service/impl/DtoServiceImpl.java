package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.dto.impl.*;
import com.ocode.cbrf.service.mapper.impl.*;
import com.ocode.cbrf.model.*;
import com.ocode.cbrf.service.DtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoServiceImpl implements DtoService {
    private final ED807MapperImpl ed807Mapper;
    private final BICDirectoryEntryMapperImpl bicDirectoryEntryMapper;
    private final ParticipantInfoMapperImpl participantInfoMapper;
    private final RestrictionListMapperImpl restrictionListMapper;
    private final AccountsMapperImpl accountsMapper;
    private final AccountsRestrictionListMapperImpl accountsRestrictionListMapper;
    private final SWBICSMapperImpl swbicsMapper;

    @Autowired
    public DtoServiceImpl(ED807MapperImpl ed807Mapper, BICDirectoryEntryMapperImpl bicDirectoryEntryMapper, ParticipantInfoMapperImpl participantInfoMapper, RestrictionListMapperImpl restrictionListMapper, AccountsMapperImpl accountsMapper, AccountsRestrictionListMapperImpl accountsRestrictionListMapper, SWBICSMapperImpl swbicsMapper) {
        this.ed807Mapper = ed807Mapper;
        this.bicDirectoryEntryMapper = bicDirectoryEntryMapper;
        this.participantInfoMapper = participantInfoMapper;
        this.restrictionListMapper = restrictionListMapper;
        this.accountsMapper = accountsMapper;
        this.accountsRestrictionListMapper = accountsRestrictionListMapper;
        this.swbicsMapper = swbicsMapper;
    }


    @Override
    public ED807 toEntities(ED807Dto ed807Dto) {
        List<BICDirectoryEntryDto> bicDirectoryEntryDtos = ed807Dto.getBicDirectoryEntries();
        List<BICDirectoryEntry> bicDirectoryEntries = bicDirectoryEntryDtos.parallelStream().map(bdeDto -> {
            BICDirectoryEntry bde = bicDirectoryEntryMapper.toEntity(bdeDto);

            if (bdeDto.getSwbicsList() != null) {
                List<SWBICS> swbics = bdeDto.getSwbicsList().parallelStream()
                        .map(swbicsMapper::toEntity)
                        .collect(Collectors.toList());
                bde.setSwbicsList(swbics);
            }

            ParticipantInfoDto participantInfoDto = bdeDto.getParticipantInfo();
            ParticipantInfo participantInfo = participantInfoMapper.toEntity(participantInfoDto);
            if (participantInfoDto.getRestrictionLists() != null) {
                List<RestrictionList> restrictionLists = participantInfoDto.getRestrictionLists().parallelStream()
                        .map(restrictionListMapper::toEntity)
                        .collect(Collectors.toList());
                participantInfo.setRestrictionLists(restrictionLists);
            }
            bde.setParticipantInfo(participantInfo);

            if (bdeDto.getAccounts() != null) {
                List<Accounts> accounts = bdeDto.getAccounts().parallelStream().map(aDto -> {
                    Accounts account = accountsMapper.toEntity(aDto);
                    if (aDto.getAccountRestrictionLists() != null) {
                        List<AccountRestrictionList> accountRestrictionLists = aDto.getAccountRestrictionLists().parallelStream()
                                .map(accountsRestrictionListMapper::toEntity)
                                .collect(Collectors.toList());
                        account.setAccountRestrictionLists(accountRestrictionLists);
                    }
                    return account;
                }).collect(Collectors.toList());
                bde.setAccounts(accounts);
            }
            return bde;
        }).toList();

        ED807 ed807 = ed807Mapper.toEntity(ed807Dto);
        ed807.setBicDirectoryEntries(bicDirectoryEntries);

        return ed807;
    }
}