package com.comp6591.service;

import java.util.List;
import java.util.Map;

public interface CalculateService {

    List<Map<String, String>> probabilityCalculator(List<Map<String, String>> JoinedTable);
}
