package com.bootcamp_2024_2.emazon.domain.spi;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;

import java.util.Optional;

public interface ICategoryPersistencePort {

    Optional<Category> findById(Long id);
    CustomPage<Category> findAll(CustomPageable page);
    Category save(Category category);
}
