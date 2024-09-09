package com.bootcamp_2024_2.emazon.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private Article article;
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        categories = List.of(
                new Category(1L, "Electronics", "Latest gadgets"),
                new Category(2L, "Home & Garden", "Decor items")
        );
        article = new Article(1L, "Smartphone", "High-end smartphone", 50, 999.99, categories);
    }

    @Test
    void getIdTest() {
        assertEquals(1L, article.getId());
    }

    @Test
    void setIdTest() {
        article.setId(2L);
        assertEquals(2L, article.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("Smartphone", article.getName());
    }

    @Test
    void setNameTest() {
        article.setName("Smartwatch");
        assertEquals("Smartwatch", article.getName());
    }

    @Test
    void getDescriptionTest() {
        assertEquals("High-end smartphone", article.getDescription());
    }

    @Test
    void setDescriptionTest() {
        article.setDescription("Premium smartwatch");
        assertEquals("Premium smartwatch", article.getDescription());
    }

    @Test
    void getQuantityTest() {
        assertEquals(50, article.getQuantity());
    }

    @Test
    void setQuantityTest() {
        article.setQuantity(100);
        assertEquals(100, article.getQuantity());
    }

    @Test
    void getPriceTest() {
        assertEquals(999.99, article.getPrice());
    }

    @Test
    void setPriceTest() {
        article.setPrice(499.99);
        assertEquals(499.99, article.getPrice());
    }

    @Test
    void getCategoriesTest() {
        assertEquals(categories, article.getCategories());
    }

    @Test
    void setCategoriesTest() {
        List<Category> newCategories = List.of(new Category(3L, "Accessories", "Phone accessories"));
        article.setCategories(newCategories);
        assertEquals(newCategories, article.getCategories());
    }
}
