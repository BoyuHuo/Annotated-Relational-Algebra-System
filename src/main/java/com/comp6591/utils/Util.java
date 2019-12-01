package com.comp6591.utils;

import com.comp6591.entity.Record;
import com.comp6591.entity.Table;

import java.util.Map;
import java.util.Random;

public class Util {

    public static Record deepCopyRecord(Record target) {
        Record copy = new Record();

        target.getFields().forEach((key, value) -> {
            copy.getFields().put(key,value);
        });

        return copy;
    }

    public static Table deepCopyTable(Table table){
        Table copy = new Table();

        table.getRecords().forEach(record -> {
            copy.getRecords().add(  deepCopyRecord(record));
        });

        return copy;
    }


    // range (0, 1]
    public static float getRandomProbability() {

        Random rand = new Random();
        int value = rand.nextInt(99);

        float prob = Float.valueOf(value + 1) / Float.valueOf(100);

        return prob;

    }

    // range [1, 10]
    public static int getRandomBag() {
        Random rand = new Random();
        int value = rand.nextInt(9);

        return value + 1;
    }

    public static int getRandomMaybe() {
        Random rand = new Random();
        int value = rand.nextInt(2);

        return value;
    }

    public static boolean isTag(String str) {

        return str.startsWith("tag")? true : false;
    }

    public static boolean mapEquals(Map<String, String> map1, Map<String, String> map2) {

        for (String key : map1.keySet()) {
            if (!key.equals("annotation") && !map1.get(key).equals(map2.get(key))) {
                return false;
            }
        }
        return true;
    }
}
