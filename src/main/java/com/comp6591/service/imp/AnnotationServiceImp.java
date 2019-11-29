package com.comp6591.service.imp;

import com.comp6591.entity.Annotation;
import com.comp6591.entity.Table;
import com.comp6591.service.AnnotationService;
import com.comp6591.service.CalcultorStrategy;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class AnnotationServiceImp implements AnnotationService {
    public static long numCounter = 0;
    public static char charCounter = 'a';

    @Override
    public Table annotationInit(Table data) {
        data.getRecords().forEach(record -> {
            String annoVal = getSequence();
            record.getFields().put("annotation", annoVal);

            Annotation annotation = new Annotation();
            annotation.setCertainty(Util.getRandomProbability());
            annotation.setBag(Util.getRandomBag());
            annotation.setCertainty(Util.getRandomProbability());
            annotation.setMaybe(Util.getRandomMaybe() == 1);
            Constants.TAGS.put(annoVal, annotation);
        });
        return data;
    }


    public static String getSequence() {
        String result = "tag";

        if (numCounter >= Long.MAX_VALUE) {
            numCounter = 0;
            charCounter++;
        }
        result += charCounter;
        result += numCounter;

        numCounter++;

        return result;
    }

    public Table calculateAnnotation(Table table, String type){

        Table result = Util.deepCopyTable(table);

        switch (type){
            case "bags":
                result.getRecords().parallelStream().forEach(record -> {
                    CalcultorStrategy calcultor = new BagCalculator();
                    String bags = calcultor.getValue(record.getFields().get("annotation"));
                    record.getFields().put("annotation",bags);
                });
                break;
            case "certainty":
                result.getRecords().parallelStream().forEach(record -> {
                    CalcultorStrategy calcultor = new CertaintyCalculator();
                    String bags = calcultor.getValue(record.getFields().get("annotation"));
                    record.getFields().put("annotation",bags);
                });
                break;
            case "probability":
                result.getRecords().parallelStream().forEach(record -> {
                    CalcultorStrategy calcultor = new CertaintyCalculator();
                    String bags = calcultor.getValue(record.getFields().get("annotation"));
                    record.getFields().put("annotation",bags);
                });
                break;
            case "maybe":
                result.getRecords().parallelStream().forEach(record -> {
                    CalcultorStrategy calcultor = new MaybeCalculator();
                    String bags = calcultor.getValue(record.getFields().get("annotation"));
                    record.getFields().put("annotation",bags);
                });
                break;
            case "polynomial": break;

            default: break;
        }
        return result;
    }


}
