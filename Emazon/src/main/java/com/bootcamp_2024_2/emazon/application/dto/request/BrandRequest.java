package com.bootcamp_2024_2.emazon.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest {

    @NotBlank(message = "Field name cannot be empty or null")
    @Size(max = 50, message = "Name must be less than or equal to 50 characters")
    private String name;

    @NotBlank(message = "Field description cannot be empty or null")
    @Size(max = 120, message = "Description must be less than or equal to 120 characters")
    private String description;
}
