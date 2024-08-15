package com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {

    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategoryList(List<CategoryEntity> categoryList);
}
