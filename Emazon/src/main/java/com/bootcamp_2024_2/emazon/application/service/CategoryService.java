package com.bootcamp_2024_2.emazon.application.service;

import com.bootcamp_2024_2.emazon.application.ports.input.CategoryServicePort;
import com.bootcamp_2024_2.emazon.application.ports.output.CategoryPersistencePort;
import com.bootcamp_2024_2.emazon.domain.exception.CategoryNotFoundException;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort persistencePort;

    @Override
    public Category findById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return persistencePort.findAll(pageable);
    }

    @Override
    public Category save(Category category) {
        return persistencePort.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        return persistencePort.findById(id)
                .map(savedCategory -> {
                    savedCategory.setName(category.getName());
                    savedCategory.setDescription(category.getDescription());
                    return persistencePort.save(savedCategory);
                })
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(persistencePort.findById(id).isEmpty()){
            throw new CategoryNotFoundException();
        }
        persistencePort.deleteById(id);
    }
}
