package com.ocode.cbrf.service.impl;

import java.io.IOException;

public interface XmlFileStorageService {
    void saveXml(String fileName, String xmlContent) throws IOException;
    String loadXml(String fileName) throws IOException;
}
