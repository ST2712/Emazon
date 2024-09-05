package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.BrandRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandHandler {

    BrandResponse findById(Long id);
    Page<BrandResponse> findAll(Pageable page);
    BrandResponse save(BrandRequest category);
    BrandResponse update(Long id, BrandRequest category);
    void deleteById(Long id);
}
