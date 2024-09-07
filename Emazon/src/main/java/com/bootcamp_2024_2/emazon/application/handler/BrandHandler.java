package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.BrandRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.BrandResponse;
import com.bootcamp_2024_2.emazon.application.mapper.request.BrandRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.response.BrandResponseMapper;
import com.bootcamp_2024_2.emazon.domain.api.IBrandServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    @Override
    public BrandResponse findById(Long id) {
        Brand brand = brandServicePort.findById(id);
        return brandResponseMapper.toResponse(brand);
    }

    @Override
    public Page<BrandResponse> findAll(Pageable pageable) {
        String sortBy = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getProperty).orElse("name");
        String sortOrder = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getDirection).orElse(Sort.Direction.ASC).name().toLowerCase();

        CustomPageable customPageable = new CustomPageable(pageable.getPageNumber(), pageable.getPageSize(), sortBy, sortOrder);
        CustomPage<Brand> customPage = brandServicePort.findAll(customPageable);
        List<BrandResponse> brandResponses = brandResponseMapper.toResponseList(customPage.getContent());
        return new PageImpl<>(brandResponses, pageable, customPage.getTotalElements());
    }

    @Override
    public BrandResponse save(BrandRequest category) {
        return brandResponseMapper.toResponse(brandServicePort.save(brandRequestMapper.toBrand(category)));
    }

}
