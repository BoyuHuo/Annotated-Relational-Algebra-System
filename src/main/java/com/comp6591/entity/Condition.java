package com.comp6591.entity;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private String operator;
    private String lhs;
    private String rhs;
}
