package com.ocode.cbrf.service;

import com.ocode.cbrf.dto.impl.ED807Dto;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface XmlFileService {
    void saveXml(String fileName, String xmlContent) throws IOException;
    String loadXml(String fileName) throws IOException;
    ED807Dto unmarshalXml(String fileName) throws JAXBException, IOException;
}
