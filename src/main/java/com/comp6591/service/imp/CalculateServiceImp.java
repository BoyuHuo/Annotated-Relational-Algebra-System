package com.comp6591.service.imp;

import com.comp6591.service.CalculateService;

import java.util.List;
import java.util.Map;

public class CalculateServiceImp implements CalculateService {


    @Override
    public List<Map<String, String>> probabilityCalculator(List<Map<String, String>> JoinedTable) {
        JoinedTable.stream()
                .parallel()
                .forEach(row -> {

                    Float value = Float.valueOf(row.get("annotation"))
                            * Float.valueOf(row.get("annotaiton"));

                    row.put("annotation", String.valueOf(value));
                });

        return JoinedTable;

    }





}
