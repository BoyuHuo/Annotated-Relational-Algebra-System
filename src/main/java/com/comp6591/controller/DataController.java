package com.comp6591.controller;

import com.comp6591.entity.DataManager;
import com.comp6591.entity.Table;
import com.comp6591.service.AnnotationService;
import com.comp6591.service.FileService;
import com.comp6591.utils.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Controller
@RequestMapping(path = "/rest/data")
public class DataController {

    @Autowired
    FileService fileService;

    @Autowired
    AnnotationService annotationService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public final Response loadData(@RequestParam("file") MultipartFile file) {
        Response.Builder responseBuilder = Response.getBuilder();
        String fileName = fileService.saveFile(file);
        Table data = fileService.readData(fileName, ",", "UTF-8");
        data = annotationService.annotationInit(data);
        DataManager.getInstance().addDataTable(fileName,data);
        return responseBuilder.setCode(Response.Code.SUCCESS).setData(fileName).build();
    }

    @ResponseBody
    @RequestMapping(path = "/datalist" ,method = RequestMethod.GET)
    public final Response getDataList() {
        Response.Builder responseBuilder = Response.getBuilder();
        Set<String> result  = DataManager.getInstance().getTableList();
        return responseBuilder.setCode(Response.Code.SUCCESS).setData(result).build();
    }

}
