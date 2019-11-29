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
    private boolean fileAsResult; // true: download as file   false: show it in plain text at front end
}
