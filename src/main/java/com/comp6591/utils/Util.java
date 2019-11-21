package com.comp6591.utils;

import com.comp6591.entity.Record;

import java.util.Random;

public class Util {

    public static Record deepCopyRecord(Record target) {
        Record copy = new Record();

        target.getFields().forEach((key, value) -> {
            copy.getFields().put(key,value);
        });

        return copy;
    }


    // range (0, 1]
    public static String getRandomProbability() {

        Random rand = new Random();
        int value = rand.nextInt(99);

        Float prob = Float.valueOf(value + 1) / Float.valueOf(100);

        return String.valueOf(prob);

    }

    // range [1, 10]
    public static String getRandomBag() {
        Random rand = new Random();
        int value = rand.nextInt(9);

        return String.valueOf(value + 1);
    }

    public static String getRandomMaybe() {
        Random rand = new Random();
        int value = rand.nextInt(1);

        return String.valueOf(value);
    }

    public static boolean isTag(String str) {

        return str.startsWith("t")? true : false;
    }
}
