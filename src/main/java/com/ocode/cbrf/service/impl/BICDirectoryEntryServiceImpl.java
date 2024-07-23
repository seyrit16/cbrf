package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.repository.BICDirectoryEntryRepository;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BICDirectoryEntry> getBICDirectoryEntriesByEd807_ID(Long edId) {
        List<BICDirectoryEntry> bicDirectoryEntries =
                bicDirectoryEntryRepository.findBICDirectoryEntriesByEd807_ID(edId);
        return bicDirectoryEntries;
    }
}
