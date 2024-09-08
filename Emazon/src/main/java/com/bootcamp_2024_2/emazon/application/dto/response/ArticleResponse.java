package com.bootcamp_2024_2.emazon.application.dto.response;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleResponse {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Category> categories;
}
