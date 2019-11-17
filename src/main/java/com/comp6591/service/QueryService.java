package com.comp6591.service;

import java.util.List;
import java.util.Map;

public interface QueryService {

    List<Map<String, String>> naturalJoin(
            List<String> keys,
            List<Map<String, String>> lTable,
            List<Map<String, String>> rTable);

    String toString(List<Map<String, String>> view);

}
