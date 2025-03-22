package org.example.utils;

//import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String value) {
        return LocalDate.parse(value, FORMATTER);
    }

    @Override
    public String marshal(LocalDate date) {
        return date.format(FORMATTER);
    }
}