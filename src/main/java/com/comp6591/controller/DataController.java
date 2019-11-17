package com.comp6591.controller;

import com.comp6591.service.FileService;
import com.comp6591.utils.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/rest/data")
public class DataController {

    @Autowired
    FileService fileService;



    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public final Response loadData(@RequestParam("file") MultipartFile file) {
        Response.Builder responseBuilder = Response.getBuilder();
        String fileName = fileService.saveFile(file);
        List<Map<String, String>> data = fileService.readData(fileName, ",", "UTF-8");
        System.out.println(data.size());

        return responseBuilder.build();
    }
}
