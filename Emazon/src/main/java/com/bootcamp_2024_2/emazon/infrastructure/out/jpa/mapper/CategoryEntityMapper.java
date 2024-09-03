package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    Category toCategory(CategoryEntity categoryEntity);
    default List<Category> toCategories(List<CategoryEntity> categoryEntities) {
        if (categoryEntities == null) {
            return List.of();
        }

        return categoryEntities.stream()
                .map(this::toCategory)
                .toList();
    }

    CategoryEntity toEntity(Category category);

}
