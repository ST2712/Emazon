package com.bootcamp_2024_2.emazon.domain.usecase;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.ICategoryPersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private Category category;
    private CustomPageable pageable;
    private CustomPage<Category> customPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category(1L, "Clothing", "Fashionable items");
        pageable = new CustomPageable(0, 10, "name", "asc");
        customPage = new CustomPage<>(Collections.singletonList(category), 0, 10, 1, 1, true);
    }

    @Test
    void findById_CategoryExists_ReturnsCategory() {
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryUseCase.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clothing", result.getName());
        assertEquals("Fashionable items", result.getDescription());
        verify(categoryPersistencePort, times(1)).findById(1L);
    }

    @Test
    void findById_CategoryDoesNotExist_ThrowsException() {
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCase.findById(1L));
        verify(categoryPersistencePort, times(1)).findById(1L);
    }

    @Test
    void findAll_ReturnsCustomPageOfCategories() {
        when(categoryPersistencePort.findAll(pageable)).thenReturn(customPage);

        CustomPage<Category> result = categoryUseCase.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(category, result.getContent().get(0));
        verify(categoryPersistencePort, times(1)).findAll(pageable);
    }

    @Test
    void save_CategoryIsSaved_ReturnsSavedCategory() {
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(category);

        Category result = categoryUseCase.save(category);

        assertNotNull(result);
        assertEquals("Clothing", result.getName());
        assertEquals("Fashionable items", result.getDescription());
        verify(categoryPersistencePort, times(1)).save(any(Category.class));
    }
}
