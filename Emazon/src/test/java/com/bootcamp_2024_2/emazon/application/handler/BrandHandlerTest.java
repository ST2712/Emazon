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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private Brand brand;
    private BrandRequest brandRequest;
    private BrandResponse brandResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brand = new Brand(1L, "Nike", "Leading sports brand.");
        brandRequest = new BrandRequest();
        brandRequest.setName("Nike");
        brandRequest.setDescription("Leading sports brand.");

        brandResponse = new BrandResponse();
        brandResponse.setId(1L);
        brandResponse.setName("Nike");
        brandResponse.setDescription("Leading sports brand.");
    }

    @Test
    void testFindById() {
        when(brandServicePort.findById(1L)).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(brandResponse);

        BrandResponse result = brandHandler.findById(1L);

        assertEquals(brandResponse, result);
        verify(brandServicePort).findById(1L);
        verify(brandResponseMapper).toResponse(brand);
    }

    @Test
    void testSave() {
        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        when(brandServicePort.save(brand)).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(brandResponse);

        BrandResponse result = brandHandler.save(brandRequest);

        assertEquals(brandResponse, result);
        verify(brandRequestMapper).toBrand(brandRequest);
        verify(brandServicePort).save(brand);
        verify(brandResponseMapper).toResponse(brand);
    }
}
