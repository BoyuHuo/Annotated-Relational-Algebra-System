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
        int value = rand.nextInt(1);

        return value;
    }

    public static boolean isTag(String str) {

        return str.startsWith("tag")? true : false;
    }
}
