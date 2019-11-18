package com.comp6591.service.imp;

import com.comp6591.service.CalculateService;

import java.util.List;
import java.util.Map;

public class CalculateServiceImp implements CalculateService {


    @Override
    public List<Map<String, String>> probabilityCalculator(List<Map<String, String>> JoinedTable) {
        JoinedTable.parallelStream()
                .forEach(row -> {

                    Float value = Float.valueOf(row.get("annotation"))
                            * Float.valueOf(row.get("annotaiton"));

                    row.put("annotation", String.valueOf(value));
                });

        return JoinedTable;

    }


    @Override
    public List<Map<String, String>> annotationInit(List<Map<String, String>> data) {
        initProbabilityAnnotation(data);
        //initBagsAnnotation();
        //initStanderedAnnotation();
        //initProvenancePolynomailsAnnotation()

        return data;
    }

    private List<Map<String, String>> initProbabilityAnnotation(List<Map<String, String>> data) {
        data.stream().forEach(row -> {
            row.put("probability_annotation", Math.random() + "");
        });
        return data;
    }

    private List<Map<String, String>> initBagsAnnotation(List<Map<String, String>> data) {
        return data;
    }

    private List<Map<String, String>> initStanderedAnnotation(List<Map<String, String>> data) {
        return data;
    }

    private List<Map<String, String>> initProvenancePolynomailsAnnotation(List<Map<String, String>> data) {
        return data;
    }


}
