package com.bootcamp_2024_2.emazon.application.mapper.response;

import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);
    default Page<CategoryResponse> toResponseList(Page<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = categories.getContent().stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(list, categories.getPageable(), categories.getTotalElements());
    }

    default List<CategoryResponse> toResponseList(List<Category> categories) {
        if (categories == null) {
            return Collections.emptyList();
        }

        return categories.stream()
                .map(this::toResponse)
                .toList();
    }

}
