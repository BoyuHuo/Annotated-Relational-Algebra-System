package com.comp6591.utils;

import java.util.HashMap;
import java.util.Map;

public class Util {

    public static Map<String, String> deepCopyMap(Map<String, String> target) {
        Map<String, String> copy = new HashMap<>();

        target.forEach(copy::put);
        return copy;
    }
}
