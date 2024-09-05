package com.bootcamp_2024_2.emazon.application.mapper.response;

import com.bootcamp_2024_2.emazon.application.dto.response.BrandResponse;
import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandResponseMapper {

    BrandResponse toResponse(Brand brand);
    default Page<BrandResponse> toResponseList(Page<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandResponse> list = brands.getContent().stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(list, brands.getPageable(), brands.getTotalElements());
    }

    default List<BrandResponse> toResponseList(List<Brand> brands) {
        if (brands == null) {
            return Collections.emptyList();
        }

        return brands.stream()
                .map(this::toResponse)
                .toList();
    }
}
