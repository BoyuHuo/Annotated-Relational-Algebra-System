package com.comp6591.controller;

import com.comp6591.entity.DataManager;
import com.comp6591.entity.DownloadFile;
import com.comp6591.entity.Table;
import com.comp6591.service.AnnotationService;
import com.comp6591.service.FileService;
import com.comp6591.utils.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
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


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> downloadBackupFile() {
        DownloadFile fileInfo = fileService.downloadFile();
        return downloadResponse(fileInfo);
    }

    protected ResponseEntity<org.springframework.core.io.Resource> downloadResponse(
            DownloadFile fileInfo) {
        File file = fileInfo.getFile();
        String fileName = fileInfo.getFileName();
        org.springframework.core.io.Resource body = new FileSystemResource(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;
        try {
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                fileName = fileName.replace("+", "%20");    // IE下载文件名空格变+号问题
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {}

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(file.length());

        return new ResponseEntity<org.springframework.core.io.Resource>(body, headers, status);
    }

}
