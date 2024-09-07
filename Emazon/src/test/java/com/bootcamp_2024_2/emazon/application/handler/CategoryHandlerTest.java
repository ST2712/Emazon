package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.application.mapper.request.CategoryRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.response.CategoryResponseMapper;
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

    private Category category;
    private CategoryRequest categoryRequest;
    private CategoryResponse categoryResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category(1L, "Clothing", "Stay stylish with our clothing range.");
        categoryRequest = new CategoryRequest();
        categoryRequest.setName("Clothing");
        categoryRequest.setDescription("Stay stylish with our clothing range.");

        categoryResponse = new CategoryResponse();
        categoryResponse.setId(1L);
        categoryResponse.setName("Clothing");
        categoryResponse.setDescription("Stay stylish with our clothing range.");
    }

    @Test
    void testFindById() {
        Long id = 1L;

        when(categoryServicePort.findById(id)).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryHandler.findById(id);

        assertEquals(categoryResponse, result);
        verify(categoryServicePort).findById(id);
        verify(categoryResponseMapper).toResponse(category);
    }

    @Test
    void testSave() {
        Category categoryToSave = new Category(null, "Clothing", "Stay stylish with our clothing range.");
        CategoryResponse expectedResponse = new CategoryResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Clothing");
        expectedResponse.setDescription("Stay stylish with our clothing range.");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(categoryToSave);
        when(categoryServicePort.save(categoryToSave)).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(expectedResponse);

        CategoryResponse result = categoryHandler.save(categoryRequest);

        assertEquals(expectedResponse, result);
        verify(categoryRequestMapper).toCategory(categoryRequest);
        verify(categoryServicePort).save(categoryToSave);
        verify(categoryResponseMapper).toResponse(category);
    }
}
