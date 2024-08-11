package com.ocode.cbrf.service;

import com.ocode.cbrf.model.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BICDirectoryEntryService {
    BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry);
    void delete(Long id);
    int update(Long edId, Map<String,String> data);
    List<BICDirectoryEntry> getAll();
    Optional<BICDirectoryEntry> getById(Long id);
    Optional<BICDirectoryEntry> getByBic(Long edId, Integer bic,Boolean showDeleted);
    Page<BICDirectoryEntry> getByEd807_ID(Long edId,Boolean showDeleted, Pageable pageable);
    Page<BICDirectoryEntry> getByParticipantNameAndParticipantType(Long edId, String piName,
                                                                   String piType,Boolean showDeleted, Pageable pageable);
}
