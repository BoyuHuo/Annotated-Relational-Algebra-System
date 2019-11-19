package com.comp6591.controller;

import com.comp6591.entity.DataManager;
import com.comp6591.entity.Table;
import com.comp6591.service.FileService;
import com.comp6591.utils.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        Table data = fileService.readData(fileName, ",", "UTF-8");
        DataManager.getInstance().addDataTable(fileName,data);
        return responseBuilder.setData(fileName).build();
    }

    @ResponseBody
    @RequestMapping(path = "datalist" ,method = RequestMethod.POST)
    public final Response getDataList(@RequestParam("file") MultipartFile file) {
        Response.Builder responseBuilder = Response.getBuilder();
        String fileName = fileService.saveFile(file);
        Table data = fileService.readData(fileName, ",", "UTF-8");
        DataManager.getInstance().addDataTable(fileName,data);
        return responseBuilder.build();
    }

}
