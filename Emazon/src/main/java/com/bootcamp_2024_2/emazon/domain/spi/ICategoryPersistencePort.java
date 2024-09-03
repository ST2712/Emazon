package com.bootcamp_2024_2.emazon.domain.spi;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryPersistencePort {

    Optional<Category> findById(Long id);
    Page<Category> findAll(Pageable page);
    Category save(Category category);
    void deleteById(Long id);
}
