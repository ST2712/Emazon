package com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;
    private String description;
}
