package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.BrandRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.BrandResponse;
import com.bootcamp_2024_2.emazon.application.mapper.request.BrandRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.response.BrandResponseMapper;
import com.bootcamp_2024_2.emazon.domain.api.IBrandServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandHandlerTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @Mock
    private BrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Brand brand = new Brand(id, "Nike", "Leading sports brand.");
        BrandResponse response = new BrandResponse();
        response.setId(id);
        response.setName("Nike");
        response.setDescription("Leading sports brand.");

        when(brandServicePort.findById(id)).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(response);

        BrandResponse result = brandHandler.findById(id);

        assertEquals(response, result);
        verify(brandServicePort).findById(id);
        verify(brandResponseMapper).toResponse(brand);
    }

    @Test
    void testSave() {
        BrandRequest request = new BrandRequest();
        request.setName("Nike");
        request.setDescription("Leading sports brand.");
        Brand brand = new Brand(null, "Nike", "Leading sports brand.");
        BrandResponse response = new BrandResponse();
        response.setId(1L);
        response.setName("Nike");
        response.setDescription("Leading sports brand.");

        when(brandRequestMapper.toBrand(request)).thenReturn(brand);
        when(brandServicePort.save(brand)).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(response);

        BrandResponse result = brandHandler.save(request);

        assertEquals(response, result);
        verify(brandRequestMapper).toBrand(request);
        verify(brandServicePort).save(brand);
        verify(brandResponseMapper).toResponse(brand);
    }
}
