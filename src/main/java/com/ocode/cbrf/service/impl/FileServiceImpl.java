package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.config.XmlStorageProperties;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.service.FileService;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

@Service
public class FileServiceImpl implements FileService {
    private JAXBContext jaxbContext;

    @PostConstruct
    public void init() throws JAXBException{
        jaxbContext = JAXBContext.newInstance(ED807Dto.class);
    }

    @Override
    public ED807Dto unmarshalXml(File file) throws JAXBException, IOException {
        Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file),
                "Windows-1251")) {
            return (ED807Dto) unmarshaller.unmarshal(reader);
        }
    }

    @Override
    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file= Files.createTempFile(null,null).toFile();
        multipartFile.transferTo(file);
        return file;
    }
}
