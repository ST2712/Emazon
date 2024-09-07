package com.bootcamp_2024_2.emazon.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomPageTest {

    private CustomPage<Category> customPage;

    @BeforeEach
    void setUp() {
        customPage = new CustomPage<>();
    }

    @Test
    void customPageBuilderTest() {
        customPage.setPageSize(10);
        customPage.setPageNumber(0);
        customPage.setTotalElements(1L);
        customPage.setTotalPages(1);
        customPage.setLast(true);
        customPage.setContent(Collections.singletonList(new Category(1L, "Clothing", "Fashionable items")));

        assertEquals(1, customPage.getContent().size());
        assertEquals(0, customPage.getPageNumber());
        assertEquals(10, customPage.getPageSize());
        assertEquals(1L, customPage.getTotalElements());
        assertEquals(1, customPage.getTotalPages());
        assertTrue(customPage.isLast());
    }

    @Test
    void customPageSettersTest() {
        customPage.setContent(Collections.singletonList(new Category(1L, "Clothing", "Fashionable items")));
        customPage.setPageNumber(0);
        customPage.setPageSize(10);
        customPage.setTotalElements(1L);
        customPage.setTotalPages(1);
        customPage.setLast(true);

        assertEquals(1, customPage.getContent().size());
        assertEquals(0, customPage.getPageNumber());
        assertEquals(10, customPage.getPageSize());
        assertEquals(1L, customPage.getTotalElements());
        assertEquals(1, customPage.getTotalPages());
        assertTrue(customPage.isLast());
    }
}
