package com.ocode.cbrf.service;

import com.ocode.cbrf.model.BICDirectoryEntry;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BICDirectoryEntryService {
    BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry);
    void delete(Long id);
    List<BICDirectoryEntry> getAll();
    Optional<BICDirectoryEntry> getByBic(Integer bic);
    Page<BICDirectoryEntry> getBICDirectoryEntriesByEd807_ID(Long edId, int page, int size);
}
