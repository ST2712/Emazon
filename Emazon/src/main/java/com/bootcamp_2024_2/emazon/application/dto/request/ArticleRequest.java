package com.bootcamp_2024_2.emazon.application.dto.request;

import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleRequest {

    @NotBlank(message = "Field name cannot be empty or null")
    private String name;

    @NotBlank(message = "Field description cannot be empty or null")
    private String description;

    @PositiveOrZero(message = "Quantity must be greater than or equal to 0")
    private int quantity;

    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    private double price;

    private Brand brand;

    @NotEmpty(message = "At least one category is required")
    @Size(min = 1, max = 3, message = "There must be between 1 and 3 categories")
    private List<Category> categories;
}
