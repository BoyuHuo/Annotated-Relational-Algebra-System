package com.comp6591.service.imp;

import com.comp6591.entity.Table;
import com.comp6591.service.CalculateService;
import com.comp6591.utils.AnnotationType;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CalculateServiceImp implements CalculateService {


    @Override
    public Table probabilityCalculator(Table JoinedTable) {
        JoinedTable.getRecords().parallelStream()
                .forEach(record -> {

                    Float value = Float.valueOf(record.getFields().get(Constants.PROBABILITY_1))
                            * Float.valueOf(record.getFields().get(Constants.PROBABILITY_2));

                    record.getFields().put(AnnotationType.PROBABILITY.name().toLowerCase(), String.valueOf(value));
                    record.getFields().remove(Constants.PROBABILITY_1);
                    record.getFields().remove(Constants.PROBABILITY_2);
                });

        return JoinedTable;

    }


    @Override
    public Table annotationInit(Table data) {
        initProbabilityAnnotation(data);
        //initBagsAnnotation();
        //initStanderedAnnotation();
        //initProvenancePolynomilesAnnotation()

        return data;
    }

    private Table initProbabilityAnnotation(Table data) {
        data.getRecords().parallelStream().forEach(row -> {
            row.getFields().put(AnnotationType.PROBABILITY.name().toLowerCase(), Util.getRandomProbability());
        });
        return data;
    }

    private List<Map<String, String>> initBagsAnnotation(List<Map<String, String>> data) {
        return data;
    }

    private List<Map<String, String>> initStanderedAnnotation(List<Map<String, String>> data) {
        return data;
    }

    private Table initProvenancePolynomailsAnnotation(Table data) {

        data.getRecords().parallelStream().forEach(row -> {
            String id = String.valueOf(data.getRecords().indexOf(row));
            row.getFields().put(AnnotationType.POLYNOMIAL.name().toLowerCase(), id);
        });

        return data;
    }


}
