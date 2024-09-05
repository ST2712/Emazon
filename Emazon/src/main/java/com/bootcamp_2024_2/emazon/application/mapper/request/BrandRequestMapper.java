package com.bootcamp_2024_2.emazon.application.mapper.request;

import com.bootcamp_2024_2.emazon.application.dto.request.BrandRequest;
import com.bootcamp_2024_2.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
    BrandRequest toRequest(Brand brand);
}
