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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_CategoryExists_ReturnsCategory() {

        Category category = new Category(1L, "Clothing", "Fashionable items");
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

        CustomPageable pageable = new CustomPageable();
        pageable.setPageNumber(0);
        pageable.setPageSize(10);
        Category category = new Category(1L, "Clothing", "Fashionable items");
        CustomPage<Category> customPage = new CustomPage<>(Collections.singletonList(category), 0, 10, 1, 1, true);
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

        Category category = new Category(1L, "Clothing", "Fashionable items");
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(category);


        Category result = categoryUseCase.save(category);


        assertNotNull(result);
        assertEquals("Clothing", result.getName());
        assertEquals("Fashionable items", result.getDescription());
        verify(categoryPersistencePort, times(1)).save(any(Category.class));
    }

    @Test
    void update_CategoryExists_UpdatesAndReturnsCategory() {

        Category existingCategory = new Category(1L, "Clothing", "Fashionable items");
        Category updatedCategory = new Category(1L, "Apparel", "Stylish apparel");
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(existingCategory);


        Category result = categoryUseCase.update(1L, updatedCategory);


        assertNotNull(result);
        assertEquals("Apparel", result.getName());
        assertEquals("Stylish apparel", result.getDescription());
        verify(categoryPersistencePort, times(1)).findById(1L);
        verify(categoryPersistencePort, times(1)).save(existingCategory);
    }

    @Test
    void update_CategoryDoesNotExist_ThrowsException() {

        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCase.update(1L, new Category()));
        verify(categoryPersistencePort, times(1)).findById(1L);
    }

    @Test
    void deleteById_CategoryExists_DeletesCategory() {

        Category category = new Category(1L, "Clothing", "Fashionable items");
        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(category));

        categoryUseCase.deleteById(1L);

        verify(categoryPersistencePort, times(1)).findById(1L);
        verify(categoryPersistencePort, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_CategoryDoesNotExist_ThrowsException() {

        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCase.deleteById(1L));
        verify(categoryPersistencePort, times(1)).findById(1L);
        verify(categoryPersistencePort, never()).deleteById(1L);
    }
}
