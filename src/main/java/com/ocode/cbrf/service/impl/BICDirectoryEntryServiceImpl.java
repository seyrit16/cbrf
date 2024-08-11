package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.ChangeType;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.repository.BICDirectoryEntryRepository;
import com.ocode.cbrf.service.BICDirectoryEntryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {
    @Autowired
    private BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Override
    @Transactional
    public BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry) {
        return bicDirectoryEntryRepository.save(bicDirectoryEntry);
    }

    @Transactional
    public void saveAll(List<BICDirectoryEntry> bicDirectoryEntries){
        bicDirectoryEntryRepository.saveAll(bicDirectoryEntries);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<BICDirectoryEntry> optionalBICDirectoryEntry = bicDirectoryEntryRepository.findById(id);
        BICDirectoryEntry bicDirectoryEntry = optionalBICDirectoryEntry.orElse(null);
        if(bicDirectoryEntry != null) {
            bicDirectoryEntry.setDeleted(true);
            save(bicDirectoryEntry);
        }
    }

    @Override
    @Transactional
    public int update(Long edId, Map<String,String> data) {
        try{
            Long id = Long.valueOf(data.get("id"));
            BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryRepository.findById(id).orElse(null);
            if (bicDirectoryEntry == null)
                return 404;

            String newBicStr = data.get("bic");
            if (newBicStr != null) {
                Integer newBic = Integer.parseInt(data.get("bic"));
                BICDirectoryEntry foundBic = getByBic(edId, newBic,true).orElse(null);
                if (foundBic != null && foundBic.getDeleted())
                    return 409;

                bicDirectoryEntry.setBic(newBic);
            }

            Optional.ofNullable(data.get("changeType")).map(ChangeType::valueOf).ifPresent(bicDirectoryEntry::setChangeType);
            bicDirectoryEntryRepository.save(bicDirectoryEntry);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
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
    public Optional<BICDirectoryEntry> getByBic(Long edId, Integer bic,Boolean showDeleted) {
        return bicDirectoryEntryRepository.findByBic(edId, bic, showDeleted);
    }

    @Override
    public Page<BICDirectoryEntry> getByEd807_ID(Long edId,Boolean showDeleted, Pageable pageable) {
        return bicDirectoryEntryRepository.findByEd807_ID(edId, showDeleted, pageable);
    }

    @Override
    public Page<BICDirectoryEntry> getByParticipantNameAndParticipantType(Long edId, String piName, String piType,Boolean showDeleted, Pageable pageable) {
        return bicDirectoryEntryRepository.findByEd807_IDAndParticipantInfo_NameAndParticipantInfo_ParticipantType(edId,piName,piType,showDeleted,pageable);
    }
}
