package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.repository.BICDirectoryEntryRepository;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {
    @Autowired
    private BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Override
    public BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry) {
        return bicDirectoryEntryRepository.save(bicDirectoryEntry);
    }

    @Override
    public void delete(Long id) {
        Optional<BICDirectoryEntry> optionalBICDirectoryEntry = bicDirectoryEntryRepository.findById(id);
        BICDirectoryEntry bicDirectoryEntry = optionalBICDirectoryEntry.orElse(null);
        if(bicDirectoryEntry != null) {
            bicDirectoryEntry.setDeleted(true);
            save(bicDirectoryEntry);
        }
    }

    @Override
    public List<BICDirectoryEntry> getAll() {
        return bicDirectoryEntryRepository.findAll();
    }

    @Override
    public Optional<BICDirectoryEntry> getByBic(Integer bic) {
        return bicDirectoryEntryRepository.findByBic(bic);
    }

    @Override
    public Page<BICDirectoryEntry> getByEd807_ID(Long edId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bicDirectoryEntryRepository.findByEd807_ID(edId, pageable);
    }

    @Override
    public Page<BICDirectoryEntry> getByParticipantNameAndParticipantType(Long edId, String piName, String piType, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return bicDirectoryEntryRepository.findByEd807_IDAndParticipantInfo_NameAndParticipantInfo_ParticipantType(edId,piName,piType,pageable);
    }
}
