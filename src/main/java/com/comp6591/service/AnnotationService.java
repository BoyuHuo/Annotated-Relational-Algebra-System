package com.comp6591.service;

import com.comp6591.entity.Table;

public interface AnnotationService {
    Table annotationInit(Table data);
    Table calculateAnnotation(Table result, String type);
}
