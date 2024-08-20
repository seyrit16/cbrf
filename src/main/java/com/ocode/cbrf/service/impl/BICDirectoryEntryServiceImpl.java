package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.exception.ConflictDataException;
import com.ocode.cbrf.invariants.ChangeType;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.repository.BICDirectoryEntryRepository;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;

    public BICDirectoryEntryServiceImpl(BICDirectoryEntryRepository bicDirectoryEntryRepository) {
        this.bicDirectoryEntryRepository = bicDirectoryEntryRepository;
    }

    @Override
    @Transactional
    public BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry) {
        return bicDirectoryEntryRepository.save(bicDirectoryEntry);
    }

    @Transactional
    public void saveAll(List<BICDirectoryEntry> bicDirectoryEntries) {
        bicDirectoryEntryRepository.saveAll(bicDirectoryEntries);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<BICDirectoryEntry> optionalBICDirectoryEntry = bicDirectoryEntryRepository.findById(id);
        BICDirectoryEntry bicDirectoryEntry = optionalBICDirectoryEntry.orElse(null);
        if (bicDirectoryEntry != null) {
            bicDirectoryEntry.setDeleted(true);
            save(bicDirectoryEntry);
        }
    }

    @Override
    @Transactional
    public void update(Long edId, Map<String, String> data) {
        Long id = Long.valueOf(data.get("id"));
        BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryRepository.findById(id).orElse(null);
        if (bicDirectoryEntry == null)
            throw new NullPointerException("bicDir is null");

        String newBicStr = data.get("bic");
        if (newBicStr != null) {
            Integer newBic = Integer.parseInt(data.get("bic"));
            BICDirectoryEntry foundBic = getByBic(edId, newBic, true).orElse(null);
            if (foundBic != null && foundBic.getDeleted())
                throw new ConflictDataException("such a bic already exists");

            bicDirectoryEntry.setBic(newBic);
        }

        Optional.ofNullable(data.get("changeType")).map(ChangeType::valueOf).ifPresent(bicDirectoryEntry::setChangeType);
        bicDirectoryEntryRepository.save(bicDirectoryEntry);
    }

    @Override
    public List<BICDirectoryEntry> getAll() {
        return bicDirectoryEntryRepository.findAll();
    }

    @Override
    public Optional<BICDirectoryEntry> getById(Long id) {
        return bicDirectoryEntryRepository.findById(id);
    }

    @Override
    public Optional<BICDirectoryEntry> getByBic(Long edId, Integer bic, Boolean showDeleted) {
        return bicDirectoryEntryRepository.findByBic(edId, bic, showDeleted);
    }

    @Override
    public Page<BICDirectoryEntry> getByEd807_ID(Long edId, Boolean showDeleted, Pageable pageable) {
        return bicDirectoryEntryRepository.findByEd807_ID(edId, showDeleted, pageable);
    }

    @Override
    public Page<BICDirectoryEntry> getByParticipantNameAndParticipantType(Long edId, String piName, String piType, Boolean showDeleted, Pageable pageable) {
        return bicDirectoryEntryRepository.findByEd807_IDAndParticipantInfo_NameAndParticipantInfo_ParticipantType(edId, piName, piType, showDeleted, pageable);
    }
}
