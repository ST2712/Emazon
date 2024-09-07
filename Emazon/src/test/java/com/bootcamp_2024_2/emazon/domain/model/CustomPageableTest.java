package com.bootcamp_2024_2.emazon.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomPageableTest {

    private CustomPageable customPageable;

    @BeforeEach
    void setUp() {
        customPageable = new CustomPageable();
    }

    @Test
    void customPageableBuilderTest() {
        customPageable = new CustomPageable(0, 10, "name", "asc");

        assertEquals(0, customPageable.getPageNumber());
        assertEquals(10, customPageable.getPageSize());
        assertEquals("name", customPageable.getSortBy());
        assertEquals("asc", customPageable.getSortOrder());
    }

    @Test
    void customPageableSettersTest() {
        customPageable.setPageNumber(1);
        customPageable.setPageSize(20);
        customPageable.setSortBy("description");
        customPageable.setSortOrder("desc");

        assertEquals(1, customPageable.getPageNumber());
        assertEquals(20, customPageable.getPageSize());
        assertEquals("description", customPageable.getSortBy());
        assertEquals("desc", customPageable.getSortOrder());
    }
}
