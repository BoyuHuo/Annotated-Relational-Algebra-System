package com.comp6591.controller;

import com.comp6591.entity.Query;
import com.comp6591.entity.Table;
import com.comp6591.service.FileService;
import com.comp6591.service.QueryService;
import com.comp6591.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/rest/query")
public class QueryController {

    @Autowired
    QueryService queryService;
    @Autowired
    FileService fileService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public final Response excuteQuery(@RequestBody Query param) {
        System.out.println(param.getQuery() + ":" + param.getType());

        Table result = queryService.doQuery(param.getQuery()).pop();

        if(param.isFileAsResult()){

            fileService.writeData("result",result);

            return Response.getBuilder()
                    .setCode(Response.Code.SUCCESS)
                    .build();
        }else {
            return Response.getBuilder()
                    .setCode(Response.Code.SUCCESS)
                    .setData(result)
                    .build();
        }
    }
}
