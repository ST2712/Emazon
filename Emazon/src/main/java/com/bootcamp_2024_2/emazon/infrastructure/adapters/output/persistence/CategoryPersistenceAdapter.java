package com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence;

import com.bootcamp_2024_2.emazon.application.ports.output.CategoryPersistencePort;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryRepository repository;
    private final CategoryPersistenceMapper mapper;

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toCategory);
    }

    @Override
    public List<Category> findAll() {
        return mapper.toCategoryList(repository.findAll());
    }

    @Override
    public List<Category> findAllOrderedByNameAsc() {
        return mapper.toCategoryList(repository.findAllOrderedByNameAsc());
    }

    @Override
    public List<Category> findAllOrderedByNameDesc() {
        return mapper.toCategoryList(repository.findAllOrderedByNameDesc());
    }

    @Override
    public Category save(Category category) {
        return mapper.toCategory(
                repository.save(mapper.toCategoryEntity(category)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
