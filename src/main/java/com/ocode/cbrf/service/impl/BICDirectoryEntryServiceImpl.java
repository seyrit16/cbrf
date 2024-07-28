package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.ChangeType;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.repository.BICDirectoryEntryRepository;
import com.ocode.cbrf.service.BICDirectoryEntryService;
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
    public int update(Long edId, Map<String,String> data) {
        try{
            Integer bic = Integer.parseInt(data.get("bic"));
            BICDirectoryEntry bicDirectoryEntry = getByBic(edId, bic).orElse(null);
            if (bicDirectoryEntry == null)
                return 404;

            String newBicStr = data.get("newBic");
            if (newBicStr != null) {
                Integer newBic = Integer.parseInt(data.get("newBic"));
                BICDirectoryEntry foundBic = getByBic(edId, newBic).orElse(null);
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
    public Optional<BICDirectoryEntry> getByBic(Long edId, Integer bic) {
        return bicDirectoryEntryRepository.findByBic(edId, bic);
    }

    @Override
    public Page<BICDirectoryEntry> getByEd807_ID(Long edId, Pageable pageable) {
        return bicDirectoryEntryRepository.findByEd807_ID(edId, pageable);
    }

    @Override
    public Page<BICDirectoryEntry> getByParticipantNameAndParticipantType(Long edId, String piName, String piType, Pageable pageable) {
        return bicDirectoryEntryRepository.findByEd807_IDAndParticipantInfo_NameAndParticipantInfo_ParticipantType(edId,piName,piType,pageable);
    }
}
