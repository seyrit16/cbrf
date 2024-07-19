package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.dto.impl.*;
import com.ocode.cbrf.dto.mapper.*;
import com.ocode.cbrf.model.*;
import com.ocode.cbrf.service.DtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoServiceImpl implements DtoService {
    @Autowired
    ED807Mapper ed807Mapper;
    @Autowired
    BICDirectoryEntryMapper bicDirectoryEntryMapper;
    @Autowired
    ParticipantInfoMapper participantInfoMapper;
    @Autowired
    RestrictionListMapper restrictionListMapper;
    @Autowired
    AccountsMapper accountsMapper;
    @Autowired
    AccountsRestrictionListMapper accountsRestrictionListMapper;
    @Autowired
    SWBICSMapper swbicsMapper;


    @Override
    public ED807 toEntities(ED807Dto ed807Dto) {
        // convert BICDirectoryEntriesDto to entity
        List<BICDirectoryEntryDto> bicDirectoryEntryDtos = ed807Dto.getBicDirectoryEntries();
        List<BICDirectoryEntry> bicDirectoryEntries = new ArrayList<>();
        for(BICDirectoryEntryDto bdeDto: bicDirectoryEntryDtos) {
            BICDirectoryEntry bde = bicDirectoryEntryMapper.toEntity(bdeDto);
            bicDirectoryEntries.add(bde);
            // convert SWBICSDto to entity, and add to BICDirectoryEntry
            List<SWBICSDto> swbicsDtos= bdeDto.getSwbicsList();
            List<SWBICS> swbics = new ArrayList<>();
            if(swbicsDtos != null){
                for(SWBICSDto swbicsDto: swbicsDtos)
                    swbics.add(swbicsMapper.toEntity(swbicsDto));
                bde.setSwbicsList(swbics);
            }
            // convert ParticipantInfoDto to entity, and add to BICDirectoryEntry
            ParticipantInfoDto participantInfoDto = bdeDto.getParticipantInfo();
            ParticipantInfo participantInfo = participantInfoMapper.toEntity(participantInfoDto);
            // convert RestrictionList to entity, and add to ParticipantInfo
            List<RestrictionListDto> restrictionListDtos = participantInfoDto.getRestrictionLists();
            List<RestrictionList> restrictionLists = new ArrayList<>();
            if(restrictionListDtos != null){
                for (RestrictionListDto rlDto : restrictionListDtos)
                    restrictionLists.add(restrictionListMapper.toEntity(rlDto));
            }
            participantInfo.setRestrictionLists(restrictionLists);
            bde.setParticipantInfo(participantInfo);
            // convert AccountsDto to entity, and add to BICDirectoryEntry
            List<AccountsDto> accountsDtos = bdeDto.getAccounts();
            List<Accounts> accounts = new ArrayList<>();
            List<AccountsRestrictionListDto> accountsRestrictionListDtos = new ArrayList<>();
            if(accountsDtos != null){
                for (AccountsDto aDto : accountsDtos)
                {
                    Accounts account = accountsMapper.toEntity(aDto);
                    // convert AccountRestrictionList to entity, and add to Accounts
                    accountsRestrictionListDtos = aDto.getAccountRestrictionLists();
                    if(accountsRestrictionListDtos != null){
                        List<AccountRestrictionList> accountRestrictionLists = new ArrayList<>();
                        for (AccountsRestrictionListDto arlDto : accountsRestrictionListDtos)
                            accountRestrictionLists.add(accountsRestrictionListMapper.toEntity(arlDto));
                        account.setAccountRestrictionLists(accountRestrictionLists);

                    }
                    accounts.add(account);
                }
            }
            bde.setAccounts(accounts);
        }
        ED807 ed807 = ed807Mapper.toEntity(ed807Dto);
        ed807.setBicDirectoryEntries(bicDirectoryEntries);
        return ed807;
    }
}
