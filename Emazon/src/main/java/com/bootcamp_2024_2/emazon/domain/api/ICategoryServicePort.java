package com.bootcamp_2024_2.emazon.domain.api;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryServicePort {

    Category findById(Long id);
    Page<Category> findAll(Pageable page);
    Category save(Category category);
    Category update(Long id, Category category);
    void deleteById(Long id);
}
