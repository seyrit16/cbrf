package com.ocode.cbrf.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, formatter);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(formatter);
    }
}
