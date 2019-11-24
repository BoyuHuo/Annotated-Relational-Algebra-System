package com.comp6591.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Annotation {

    private boolean maybe;

    private int bag;

    private float probability;

    private float certainty;

    public boolean getMayBe() {
        return maybe;
    }
}
