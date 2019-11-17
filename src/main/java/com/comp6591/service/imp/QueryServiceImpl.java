package com.comp6591.service.imp;

import com.comp6591.service.QueryService;
import com.comp6591.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryServiceImpl implements QueryService {

    public String toString(List<Map<String, String>> view) {
        StringBuilder result = new StringBuilder();
        view.forEach(record -> {

            record.forEach((key, value) -> {
                result.append(key)
                        .append(":")
                        .append(value)
                        .append(" ");
            });

            result.append("\n");
        });
        return result.toString();
    }

    public List<Map<String, String>> naturalJoin(
            List<String> keys,
            List<Map<String, String>> lTable,
            List<Map<String, String>> rTable) {

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        lTable.forEach(lRecord -> {
            rTable.forEach(rRecord -> {

                if (isEqual(keys, lRecord, rRecord)) {
                    result.add(joinRecord(keys,lRecord,rRecord));
                }

            });
        });

        return result;
    }

    public List<Map<String, String>> project(List<String> keys, List<Map<String, String>> table) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        table.forEach(record -> {
            Map<String, String> newRecord = new HashMap<>();
            record.forEach((key, value) -> {
                if(keys.contains(key)) {
                    newRecord.put(key, value);
                }
            });
            if (newRecord.size() > 0) {
                result.add(newRecord);
            }
        });
        return result;
    }

    private boolean isEqual(List<String> keys, Map<String, String> lRecord, Map<String, String> rRecord) {

        for (int i = 0; i < keys.size(); i ++) {
            if (!lRecord.get(keys.get(i)).equals(rRecord.get(keys.get(i)))) {
                return false;
            }
        }

        return true;
    }

    private Map<String, String> joinRecord(List<String> keys, Map<String, String> lRecord, Map<String, String> rRecord) {

        Map<String, String> result = Util.deepCopyMap(lRecord);
        rRecord.forEach((key,value) -> {
            if (!keys.contains(key)) {
                result.put(key,rRecord.get(key));
            }
        });
        return result;
    }

}
