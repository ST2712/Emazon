package com.bootcamp_2024_2.emazon.domain.api;

import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;

public interface IBrandServicePort {

    Brand findById(Long id);
    CustomPage<Brand> findAll(CustomPageable page);
    Brand save(Brand category);
}
