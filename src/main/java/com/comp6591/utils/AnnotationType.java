package com.comp6591.utils;

public enum AnnotationType {

    BAG, PROPABILITY, CERTAINTY, POLINOMINAL, PRESENCE;

    public int getValue() {
        return ordinal() + 1;
    }
}


