package com.comp6591.service.imp;

import com.comp6591.entity.DataManager;
import com.comp6591.entity.Record;
import com.comp6591.entity.Table;
import com.comp6591.service.QueryService;
import org.junit.jupiter.api.Test;

import java.util.*;

class QueryServiceImplTest {

    @Test
    void testBuildInputStack() {

        QueryService queryService = new QueryServiceImpl();
        String input = "project <A,C> (q join r)";
        queryService.buildInputStack(input);
        Stack<String> tokens = queryService.getInputStack();
        tokens.forEach(System.out::println);

    }

    @Test
    void testQuery() {

        QueryService queryService = new QueryServiceImpl();

        Table lTable = new Table();
        Table rTable = new Table();

        Map<String, String> lRecord1 = new HashMap<String, String>(){
            {
                put("A", "6");
                put("B", "7");
                put("C", "8");
            }
        };

        Map<String, String> lRecord2 = new HashMap<String, String>(){
            {
                put("A", "7");
                put("B", "8");
                put("C", "9");
            }
        };

        Map<String, String> lRecord3 = new HashMap<String, String>(){
            {
                put("A", "9");
                put("B", "9");
                put("C", "9");
            }
        };

        Map<String, String> rRecord1 = new HashMap<String, String>(){
            {
                put("B", "7");
                put("F", "6");
            }
        };

        Map<String, String> rRecord2 = new HashMap<String, String>(){
            {
                put("B", "7");
                put("F", "9");
            }
        };

        Map<String, String> rRecord3 = new HashMap<String, String>(){
            {
                put("B", "9");
                put("F", "1");
            }
        };

        Map<String, String> rRecord4 = new HashMap<String, String>(){
            {
                put("B", "9");
                put("F", "2");
            }
        };

        lTable.getRecords().add(new Record(lRecord1));
        lTable.getRecords().add(new Record(lRecord2));
        lTable.getRecords().add(new Record(lRecord3));

        rTable.getRecords().add(new Record(rRecord1));
        rTable.getRecords().add(new Record(rRecord2));
        rTable.getRecords().add(new Record(rRecord3));
        rTable.getRecords().add(new Record(rRecord4));

        List<String> joinKeys = new ArrayList<String>() {{
                add("B");
        }};

        System.out.println(lTable.toString());
        System.out.println(rTable.toString());

        Table joinResult = queryService.naturalJoin(joinKeys,lTable,rTable);
        System.out.println(joinResult.toString());

        List<String> projectKeys = new ArrayList<String>() {{
            add("B");
            add("C");
            add("F");
        }};

        Table projectResult = queryService.project(projectKeys, joinResult);
        System.out.println(projectResult.toString());

        DataManager.getInstance().addDataTable("test",lTable);

        queryService.buildInputStack("project <B,C> test");
        queryService.doQuery();

        System.out.println(queryService.getTableStack().pop().toString());

    }
}