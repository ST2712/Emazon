package com.bootcamp_2024_2.emazon.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    void brandBuilderTest() {
        Brand brand = new Brand(1L, "Nike", "Just do it");

        assertEquals(1L, brand.getId());
        assertEquals("Nike", brand.getName());
        assertEquals("Just do it", brand.getDescription());
    }

    @Test
    void BrandSettersTest() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Adidas");
        brand.setDescription("Impossible is nothing");

        assertEquals(1L, brand.getId());
        assertEquals("Adidas", brand.getName());
        assertEquals("Impossible is nothing", brand.getDescription());
    }
}