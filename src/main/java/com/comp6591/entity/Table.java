package com.comp6591.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    private List<Record> records = new ArrayList<>();

    public String toString() {
        StringBuilder result = new StringBuilder();
        records.forEach(record -> {

            record.getFields().forEach((key, value) -> {
                result.append(key)
                        .append(":")
                        .append(value)
                        .append(" ");
            });

            result.append("\n");
        });
        return result.toString();
    }
}
