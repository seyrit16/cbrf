package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.RestrictionListDto;
import com.ocode.cbrf.model.RestrictionList;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

@Service
public class RestrictionListMapperImpl extends AbstractCbrfMapper<RestrictionList, RestrictionListDto> {
    public RestrictionListMapperImpl() {
        super(RestrictionList.class, RestrictionListDto.class);
    }
}
