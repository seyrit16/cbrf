package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.Rstr;
import com.ocode.cbrf.model.RestrictionList;
import com.ocode.cbrf.repository.RestrictionListRepository;
import com.ocode.cbrf.service.RestrictionListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class RestrictionListServiceImpl implements RestrictionListService {
    private final RestrictionListRepository restrictionListRepository;

    @Autowired
    public RestrictionListServiceImpl(RestrictionListRepository restrictionListRepository) {
        this.restrictionListRepository = restrictionListRepository;
    }

    @Override
    @Transactional
    public RestrictionList save(RestrictionList restrictionList) {
        return restrictionListRepository.save(restrictionList);
    }

    @Override
    @Transactional
    public void update(Long id, Map<String, String> data) {
        RestrictionList restrictionList = getById(id).orElse(null);
        if(restrictionList == null) throw new NullPointerException("restriction list is null");

        Optional.ofNullable(data.get("restriction")).map(Rstr::valueOf).ifPresent(restrictionList::setRestriction);
        Optional.ofNullable(data.get("date"))
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ifPresent(restrictionList::setDate);

        restrictionListRepository.save(restrictionList);
    }

    @Override
    public Optional<RestrictionList> getById(Long id) {
        return restrictionListRepository.findById(id);
    }
}
