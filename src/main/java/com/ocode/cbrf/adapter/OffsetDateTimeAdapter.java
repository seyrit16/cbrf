package com.ocode.cbrf.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeAdapter extends XmlAdapter<String, OffsetDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ssZ");

    @Override
    public OffsetDateTime unmarshal(String s) throws Exception {
        return OffsetDateTime.parse(s, formatter);
    }

    @Override
    public String marshal(OffsetDateTime offsetDateTime) throws Exception {
        return offsetDateTime.format(formatter);
    }
}
