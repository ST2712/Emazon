package com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.request.CategoryCreateRequest;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRestMapper {

    Category toCategory(CategoryCreateRequest categoryCreateRequest);
    CategoryResponse toCategoryResponse(Category category);
    default Page<CategoryResponse> toCategoryResponseList(Page<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = categories.getContent().stream()
                .map(this::toCategoryResponse)
                .toList();

        return new PageImpl<>(list, categories.getPageable(), categories.getTotalElements());
    }

}
