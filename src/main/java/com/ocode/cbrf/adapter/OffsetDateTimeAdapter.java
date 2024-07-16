package com.ocode.cbrf.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeAdapter extends XmlAdapter<String, OffsetDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public OffsetDateTime unmarshal(String s) throws Exception {
        return OffsetDateTime.parse(s, formatter);
    }

    @Override
    public String marshal(OffsetDateTime offsetDateTime) throws Exception {
        return offsetDateTime.format(formatter);
    }
}
