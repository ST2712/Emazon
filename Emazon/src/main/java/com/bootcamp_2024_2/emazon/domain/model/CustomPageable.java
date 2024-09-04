package com.bootcamp_2024_2.emazon.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageable {
    private int pageNumber;
    private int pageSize;
    private String sortBy;
    private String sortOrder;
}
