package com.ocode.cbrf.service;

import com.ocode.cbrf.model.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BICDirectoryEntryService {
    BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry);
    void delete(Long id);
    List<BICDirectoryEntry> getAll();
    Optional<BICDirectoryEntry> getByBic(Integer bic);
    Page<BICDirectoryEntry> getByEd807_ID(Long edId, Pageable pageable);
    Page<BICDirectoryEntry> getByParticipantNameAndParticipantType(Long edId, String piName,
                                                                   String piType, Pageable pageable);
}
