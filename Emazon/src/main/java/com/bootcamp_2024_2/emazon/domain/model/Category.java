package com.bootcamp_2024_2.emazon.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String description;
}
