package com.comp6591.controller;

import com.comp6591.entity.Query;
import com.comp6591.entity.Table;
import com.comp6591.entity.TableWIthDuration;
import com.comp6591.service.AnnotationService;
import com.comp6591.service.FileService;
import com.comp6591.service.QueryService;
import com.comp6591.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;
import java.time.Instant;

@Controller
@RequestMapping(path = "/rest/query")
public class QueryController {

    @Autowired
    QueryService queryService;
    @Autowired
    FileService fileService;
    @Autowired
    AnnotationService annotationService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public final Response excuteQuery(@RequestBody Query param) {

        System.out.println(param.getQuery() + ":" + param.getType());

        Instant start = Instant.now();
        Table result = queryService.doQuery(param.getQuery()).pop();

        result = annotationService.calculateAnnotation(result,param.getType());

        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();

        TableWIthDuration tableWIthDuration = new TableWIthDuration(result, duration,0);

        if(param.isFileAsResult()){

            fileService.writeData("result",result);

            tableWIthDuration.setType(1);

            return Response.getBuilder()
                    .setCode(Response.Code.SUCCESS)
                    .setData(tableWIthDuration)
                    .build();
        }else {
            return Response.getBuilder()
                    .setCode(Response.Code.SUCCESS)
                    .setData(tableWIthDuration)
                    .build();
        }
    }
}
