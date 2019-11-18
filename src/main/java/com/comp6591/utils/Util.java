package com.comp6591.utils;

import com.comp6591.entity.Record;

public class Util {

    public static Record deepCopyRecord(Record target) {
        Record copy = new Record();

        target.getFields().forEach((key, value) -> {
            copy.getFields().put(key,value);
        });

        return copy;
    }
}
