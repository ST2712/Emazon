package com.bootcamp_2024_2.emazon.application.ports.output;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {

    Optional<Category> findById(Long id);
    Page<Category> findAll(Pageable page);
    Category save(Category category);
    void deleteById(Long id);
}
