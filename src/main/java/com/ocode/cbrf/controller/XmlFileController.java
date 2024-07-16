package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.service.impl.XmlFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/xml_file")
public class XmlFileController {
    @Autowired
    XmlFileServiceImpl xmlFileService;

    @GetMapping("/upload")
    public ResponseEntity<String> uploadFile(){
        try{
            String fileName = "20220630_ED807_full.xml";
            ED807Dto ed807Dto = xmlFileService.unmarshalXml(fileName);
            System.out.println(ed807Dto.toString());
            //System.out.println("num:"+ ed807Dto.getNumber()+", crdt: " + ed807Dto.getCreationDateTime()+", bd:"
            // +ed807Dto.getBusinessDay());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
