package com.comp6591.service;

import com.comp6591.entity.Table;

import java.util.List;
import java.util.Map;

public interface CalculateService {

    Table probabilityCalculator(Table JoinedTable);

    Table annotationInit(Table data);
}
