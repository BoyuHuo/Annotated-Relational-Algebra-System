package com.comp6591.service;

import com.comp6591.entity.Table;

import java.util.List;
import java.util.Stack;

public interface QueryService {

    Table naturalJoin(Table lTable, Table rTable);

    Table project(List<String> keys, Table table);

    Stack<Table> doQuery(String query, String type);
  
    Table union(Table lTable, Table rTable);
}
