package com.comp6591.entity;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query {
    private String query;
    private String type;  //Probablity , Standered , Bags, Polynomial , ...
}
