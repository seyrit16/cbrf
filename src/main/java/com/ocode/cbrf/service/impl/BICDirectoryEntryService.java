package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.BICDirectoryEntry;

import java.util.List;
import java.util.Optional;

public interface BICDirectoryEntryService {
    BICDirectoryEntry save(BICDirectoryEntry bicDirectoryEntry);
    void delete(Long id);
    List<BICDirectoryEntry> getAll();
    Optional<BICDirectoryEntry> getByBic(Integer bic);
}
