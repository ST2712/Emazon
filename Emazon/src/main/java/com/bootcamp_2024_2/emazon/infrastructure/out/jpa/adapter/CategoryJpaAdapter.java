package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.adapter;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.ICategoryPersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.CategoryNotFoundException;
import com.bootcamp_2024_2.emazon.infrastructure.exception.NoDataFoundException;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Optional<Category> findById(Long id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
        return categoryEntityOptional.map(categoryEntityMapper::toCategory);
    }

    private Pageable toPageable(CustomPageable customPageable) {
        return PageRequest.of(customPageable.getPageNumber(), customPageable.getPageSize());
    }

    @Override
    public CustomPage<Category> findAll(CustomPageable pageable) {
        Pageable pageableJpa = toPageable(pageable);
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageableJpa);
        if (categoryEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        List<Category> categories = categoryEntityMapper.toCategories(categoryEntityPage.getContent());
        CustomPage<Category> customPage = new CustomPage<>();
        customPage.setContent(categories);
        customPage.setTotalElements(categoryEntityPage.getTotalElements());
        customPage.setPageNumber(pageable.getPageNumber());
        customPage.setPageSize(pageable.getPageSize());
        return customPage;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
        return categoryEntityMapper.toCategory(savedCategoryEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }
        categoryRepository.deleteById(id);
    }
}
