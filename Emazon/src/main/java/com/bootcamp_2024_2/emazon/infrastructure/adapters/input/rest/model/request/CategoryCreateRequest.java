package com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequest {

    @NotBlank(message = "Field name cannot be empty or null")
    @Size(max = 50, message = "Name must be less than or equal to 50 characters")
    private String name;

    @NotBlank(message = "Field description cannot be empty or null")
    @Size(max = 90, message = "Description must be less than or equal to 90 characters")
    private String description;
}
