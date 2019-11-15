package com.comp6591.controller;

import com.comp6591.utils.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping(path = "/rest/picture")
public class DemoController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public final Response testAPI(@RequestParam("file") MultipartFile file) {
        Response.Builder responseBuilder = Response.getBuilder();

        return responseBuilder.build();
    }
}
