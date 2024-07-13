package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.dto.impl.ED807Dto;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlFileService {
    void saveXml(String fileName, String xmlContent) throws IOException;
    String loadXml(String fileName) throws IOException;
    ED807Dto unmarshalXml(String path) throws JAXBException, IOException;
}
