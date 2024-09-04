package com.bootcamp_2024_2.emazon.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomPageableTest {

    @Test
    void customPageableBuilderTest() {
        CustomPageable customPageable = new CustomPageable(0, 10, "name", "asc");

        assertEquals(0, customPageable.getPageNumber());
        assertEquals(10, customPageable.getPageSize());
        assertEquals("name", customPageable.getSortBy());
        assertEquals("asc", customPageable.getSortOrder());
    }

    @Test
    void customPageableSettersTest() {
        CustomPageable customPageable = new CustomPageable();
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

