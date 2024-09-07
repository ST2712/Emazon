package com.bootcamp_2024_2.emazon.domain.spi;

import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;

import java.util.Optional;

public interface IBrandPersistencePort {

    Optional<Brand> findById(Long id);
    CustomPage<Brand> findAll(CustomPageable page);
    Brand save(Brand brand);
}
