package com.ocode.cbrf.service;

import com.ocode.cbrf.dto.impl.ED807Dto;
import jakarta.xml.bind.JAXBException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
    ED807Dto unmarshalXml(File file) throws JAXBException, IOException;
    File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException;
}
