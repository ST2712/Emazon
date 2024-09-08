package com.bootcamp_2024_2.emazon.domain.model;

public class CategorySummary {
    private Long id;
    private String name;

    public CategorySummary(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategorySummary() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
