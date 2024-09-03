package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryHandler {

    CategoryResponse findById(Long id);
    Page<CategoryResponse> findAll(Pageable page);
    CategoryResponse save(CategoryRequest category);
    CategoryResponse update(Long id, CategoryRequest category);
    void deleteById(Long id);
}
