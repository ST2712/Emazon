package com.bootcamp_2024_2.emazon.domain.api;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;

public interface ICategoryServicePort {

    Category findById(Long id);
    CustomPage<Category> findAll(CustomPageable page);
    Category save(Category category);
}
