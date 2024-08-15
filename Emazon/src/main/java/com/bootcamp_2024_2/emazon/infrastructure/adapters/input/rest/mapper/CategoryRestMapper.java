package com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.request.CategoryCreateRequest;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRestMapper {

    Category toCategory(CategoryCreateRequest categoryCreateRequest);
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
