package com.bootcamp_2024_2.emazon.application.mapper.request;

import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {
    Category toCategory(CategoryRequest categoryRequest);
    CategoryRequest toRequest(Category category);
}
