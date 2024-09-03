package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_2.emazon.domain.api.ICategoryServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryServicePort.findById(id);
        return categoryResponseMapper.toResponse(category);
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable page) {
        return categoryResponseMapper.toResponseList(categoryServicePort.findAll(page));
    }

    @Override
    public CategoryResponse save(CategoryRequest category) {
        return categoryResponseMapper.toResponse(categoryServicePort.save(categoryRequestMapper.toCategory(category)));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest category) {
        return categoryResponseMapper.toResponse(categoryServicePort.update(id,
                categoryRequestMapper.toCategory(category)));
    }

    @Override
    public void deleteById(Long id) {
        categoryServicePort.deleteById(id);
    }
}
