package com.comp6591.service;

import com.comp6591.entity.Table;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    String saveFile(MultipartFile file);
    Table readData(String filename, String regex, String encoding);
    void writeData(String fileName, Table result);
}
