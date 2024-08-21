package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.SWBICSDto;
import com.ocode.cbrf.model.SWBICS;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

@Service
public class SWBICSMapperImpl extends AbstractCbrfMapper<SWBICS, SWBICSDto> {
    public SWBICSMapperImpl() {
        super(SWBICS.class, SWBICSDto.class);
    }
}
