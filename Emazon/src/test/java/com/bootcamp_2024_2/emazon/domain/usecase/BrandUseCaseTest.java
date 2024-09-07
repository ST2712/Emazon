package com.bootcamp_2024_2.emazon.domain.usecase;

import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.IBrandPersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.BrandNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByYd_BrandsExists_ReturnsBrand() {

        Brand brand = new Brand(1L, "Nike", "Just do it");
        when(brandPersistencePort.findById(1L)).thenReturn(Optional.of(brand));

        Brand result = brandUseCase.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Nike", result.getName());
        assertEquals("Just do it", result.getDescription());
        verify(brandPersistencePort, times(1)).findById(1L);
    }

    @Test
    void findById_BrandDoesNotExist_ThrowsException() {

        when(brandPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> brandUseCase.findById(1L));
        verify(brandPersistencePort, times(1)).findById(1L);
    }

    @Test
    void findAll_ReturnsCustomPageOfBrands() {

        CustomPageable pageable = new CustomPageable();
        pageable.setPageNumber(0);
        pageable.setPageSize(10);
        Brand brand = new Brand(1L, "Nike", "Just do it");
        CustomPage<Brand> customPage = new CustomPage<>(Collections.singletonList(brand), 0, 10, 1, 1, true);
        when(brandPersistencePort.findAll(pageable)).thenReturn(customPage);

        CustomPage<Brand> result = brandUseCase.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(1, result.getTotalElements());
        assertEquals(brand, result.getContent().get(0));
        verify(brandPersistencePort, times(1)).findAll(pageable);
    }

    @Test
    void save_BrandIsSaved_ReturnSavedBrand() {

        Brand brand = new Brand(1L, "Nike", "Just do it");
        when(brandPersistencePort.save(any(Brand.class))).thenReturn(brand);

        Brand result = brandUseCase.save(brand);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Nike", result.getName());
        assertEquals("Just do it", result.getDescription());
        verify(brandPersistencePort, times(1)).save(brand);
    }
}