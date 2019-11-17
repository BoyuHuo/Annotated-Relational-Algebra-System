package com.comp6591.service;

import com.comp6591.entity.Table;

import java.util.List;

public interface QueryService {

    Table naturalJoin(
            List<String> keys,
            Table lTable,
            Table rTable);

    Table project(List<String> keys, Table table);
}
