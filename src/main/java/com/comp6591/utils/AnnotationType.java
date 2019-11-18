package com.comp6591.utils;

public enum AnnotationType {

    BAG, PROBABILITY, CERTAINTY, POLYNOMIAL, PRESENCE;

    public int getValue() {
        return ordinal() + 1;
    }
}


