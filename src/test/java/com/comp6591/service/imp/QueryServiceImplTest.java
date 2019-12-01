package com.comp6591.service.imp;

import com.comp6591.entity.Condition;
import com.comp6591.entity.DataManager;
import com.comp6591.entity.Record;
import com.comp6591.entity.Table;
import com.comp6591.service.QueryService;
import org.junit.jupiter.api.Test;

import java.util.*;

class QueryServiceImplTest {

    @Test
    void testQuery() {

        QueryService queryService = new QueryServiceImpl();

        Table lTable = new Table();
        Table rTable = new Table();
        Table mTable = new Table();
        Table nTable = new Table();

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

        Map<String, String> mRecord1 = new HashMap<String, String>(){
            {
                put("F", "9");
                put("G", "2");
            }
        };

        Map<String, String> mRecord2 = new HashMap<String, String>(){
            {
                put("F", "1");
                put("G", "3");
            }
        };

        Map<String, String> mRecord3 = new HashMap<String, String>(){
            {
                put("F", "2");
                put("G", "4");
            }
        };

        Map<String, String> nRecord1 = new HashMap<String, String>(){
            {
                put("F", "1");
                put("G", "3");
            }
        };

        Map<String, String> nRecord2 = new HashMap<String, String>(){
            {
                put("F", "3");
                put("G", "5");
            }
        };


        lTable.getRecords().add(new Record(lRecord1));
        lTable.getRecords().add(new Record(lRecord2));
        lTable.getRecords().add(new Record(lRecord3));

        rTable.getRecords().add(new Record(rRecord1));
        rTable.getRecords().add(new Record(rRecord2));
        rTable.getRecords().add(new Record(rRecord3));
        rTable.getRecords().add(new Record(rRecord4));

        mTable.getRecords().add(new Record(mRecord1));
        mTable.getRecords().add(new Record(mRecord2));
        mTable.getRecords().add(new Record(mRecord3));

        nTable.getRecords().add(new Record(nRecord1));
        nTable.getRecords().add(new Record(nRecord2));


        System.out.println(lTable.toString());
        System.out.println(rTable.toString());

        Table joinResult = queryService.naturalJoin(lTable,rTable);
        System.out.println(joinResult.toString());
        DataManager.getInstance().addDataTable("lrTable",joinResult);

        List<String> projectKeys = new ArrayList<String>() {{
            add("B");
            add("C");
            add("F");
        }};

        Table projectResult = queryService.project(projectKeys, joinResult);
        System.out.println(projectResult.toString());

        DataManager.getInstance().addDataTable("test1",lTable);
        DataManager.getInstance().addDataTable("test2", rTable);
        DataManager.getInstance().addDataTable("test3", mTable);
        DataManager.getInstance().addDataTable("test4", nTable);

        Table result1 = queryService.doQuery("project <B,F> ( test1 join test2 )").pop();

        System.out.println(result1.toString());

        Table result2 = queryService.doQuery("project <A,B,C,F,G> ( lrTable join test3 )").pop();
        System.out.println(result2.toString());

        Table result3 = queryService.doQuery("project <A,B,C,F,G> ( ( test1 join test2 ) join test3 )").pop();
        System.out.println(result3.toString());

        Table result4 = queryService.doQuery("project <F,G> ( test3 union test4 )").pop();
        System.out.println(result4.toString());

        System.out.println("test select.");
        System.out.println(lTable.toString());

        List<Condition> and = new ArrayList<>();
        and.add(Condition.builder().operator(">").lhs("C").rhs("8").build());
        List<Condition> or = new ArrayList<>();
        or.add(Condition.builder().operator("=").lhs("A").rhs("6").build());

        System.out.println(queryService.select(lTable, and ,or).toString());

    }
}