package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.application.mapper.CategoryRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.CategoryResponseMapper;
import com.bootcamp_2024_2.emazon.domain.api.ICategoryServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Category category = new Category(id, "Clothing", "Stay stylish with our clothing range.");
        CategoryResponse response = new CategoryResponse();
        response.setId(id);
        response.setName("Clothing");
        response.setDescription("Stay stylish with our clothing range.");

        when(categoryServicePort.findById(id)).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(response);

        CategoryResponse result = categoryHandler.findById(id);

        assertEquals(response, result);
        verify(categoryServicePort).findById(id);
        verify(categoryResponseMapper).toResponse(category);
    }


    @Test
    void testSave() {
        CategoryRequest request = new CategoryRequest();
        request.setName("Clothing");
        request.setDescription("Stay stylish with our clothing range.");
        Category category = new Category(null, "Clothing", "Stay stylish with our clothing range.");
        CategoryResponse response = new CategoryResponse();
        response.setId(1L);
        response.setName("Clothing");
        response.setDescription("Stay stylish with our clothing range.");

        when(categoryRequestMapper.toCategory(request)).thenReturn(category);
        when(categoryServicePort.save(category)).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(response);

        CategoryResponse result = categoryHandler.save(request);

        assertEquals(response, result);
        verify(categoryRequestMapper).toCategory(request);
        verify(categoryServicePort).save(category);
        verify(categoryResponseMapper).toResponse(category);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        CategoryRequest request = new CategoryRequest();
        request.setName("Clothing");
        request.setDescription("Stay stylish with our clothing range.");
        Category category = new Category(id, "Clothing", "Stay stylish with our clothing range.");
        CategoryResponse response = new CategoryResponse();
        response.setId(id);
        response.setName("Clothing");
        response.setDescription("Stay stylish with our clothing range.");

        when(categoryRequestMapper.toCategory(request)).thenReturn(category);
        when(categoryServicePort.update(id, category)).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(response);

        CategoryResponse result = categoryHandler.update(id, request);

        assertEquals(response, result);
        verify(categoryRequestMapper).toCategory(request);
        verify(categoryServicePort).update(id, category);
        verify(categoryResponseMapper).toResponse(category);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        doNothing().when(categoryServicePort).deleteById(id);

        categoryHandler.deleteById(id);

        verify(categoryServicePort).deleteById(id);
    }
}
