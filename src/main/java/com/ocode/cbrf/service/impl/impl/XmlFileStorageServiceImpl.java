package com.ocode.cbrf.service.impl.impl;

import com.ocode.cbrf.config.XmlStorageProperties;
import com.ocode.cbrf.service.impl.XmlFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class XmlFileStorageServiceImpl implements XmlFileStorageService {
    private final String storagePath;

    @Autowired
    public XmlFileStorageServiceImpl(XmlStorageProperties properties){
        this.storagePath = properties.getPath();
    }

    @Override
    public void saveXml(String fileName, String xmlContent) throws IOException {
        File file = new File(storagePath, fileName);
        try(FileWriter writer = new FileWriter(file)){
            writer.write(xmlContent);
        }
    }

    @Override
    public String loadXml(String fileName) throws IOException {
        File file = new File(storagePath, fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
