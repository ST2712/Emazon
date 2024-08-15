package com.bootcamp_2024_2.emazon.application.ports.input;

import com.bootcamp_2024_2.emazon.domain.model.Category;

import java.util.List;

public interface CategoryServicePort {

    Category findById(Long id);
    List<Category> findAll();
    Category save(Category category);
    Category update(Long id, Category category);
    void deleteById(Long id);
}
