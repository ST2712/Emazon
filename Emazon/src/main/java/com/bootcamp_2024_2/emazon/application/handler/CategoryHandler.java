package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_2.emazon.domain.api.ICategoryServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Page<CategoryResponse> findAll(Pageable pageable) {
        String sortBy = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getProperty).orElse("name");
        String sortOrder = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getDirection).orElse(Sort.Direction.ASC).name().toLowerCase();

        CustomPageable customPageable = new CustomPageable(pageable.getPageNumber(), pageable.getPageSize(), sortBy, sortOrder);
        CustomPage<Category> customPage = categoryServicePort.findAll(customPageable);
        List<CategoryResponse> categoryResponses = categoryResponseMapper.toResponseList(customPage.getContent());
        return new PageImpl<>(categoryResponses, pageable, customPage.getTotalElements());
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
