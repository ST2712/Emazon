package com.bootcamp_2024_2.emazon.application.ports.output;

import com.bootcamp_2024_2.emazon.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {

    Optional<Category> findById(Long id);
    List<Category> findAll();
    Category save(Category category);
    void deleteById(Long id);
}
