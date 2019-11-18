package com.comp6591.entity;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    Map<String, String> fields = new HashMap<>();
}
