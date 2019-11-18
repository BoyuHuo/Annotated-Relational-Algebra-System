package com.comp6591.controller;

import com.comp6591.entity.Query;
import com.comp6591.utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/rest/query")
public class QueryController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public final Response excuteQuery(@RequestBody Query param) {
        Response.Builder responseBuilder = Response.getBuilder();
        System.out.println(param.getQuery() + ":" + param.getType());
        return responseBuilder.build();
    }
}
