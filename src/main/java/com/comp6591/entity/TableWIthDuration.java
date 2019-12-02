package com.comp6591.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableWIthDuration {

    Table table;

    long duration;

    int type;
}
