package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.config.XmlStorageProperties;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.service.XmlFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class XmlFileServiceImpl implements XmlFileService {
    private final String storagePath;

    @Autowired
    public XmlFileServiceImpl(XmlStorageProperties properties){
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

    @Override
    public ED807Dto unmarshalXml(String path) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(ED807Dto.class);
        return (ED807Dto) context.createUnmarshaller()
                .unmarshal(new FileReader(path));
    }
}
