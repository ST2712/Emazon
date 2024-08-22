package com.bootcamp_2024_2.emazon.application.ports.input;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryServicePort {

    Category findById(Long id);
    Page<Category> findAll(Pageable page);
    Category save(Category category);
    Category update(Long id, Category category);
    void deleteById(Long id);
}
