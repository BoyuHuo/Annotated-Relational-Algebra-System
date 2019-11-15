package com.comp6591.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {
    String saveFile(MultipartFile file);
    List<Map<String, String>> readData(String filename, String regex, String encoding);
}
