package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper;

import com.bootcamp_2024_2.emazon.application.dto.response.CategoryResponse;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    Category toCategory(CategoryEntity categoryEntity);
    default Page<Category> toPage(Page<CategoryEntity> categories) {
        if (categories == null) {
            return null;
        }

        List<Category> list = categories.getContent().stream()
                .map(category -> {
                    Category newCategory = new Category();
                    newCategory.setId(category.getId());
                    newCategory.setName(category.getName());
                    newCategory.setDescription(category.getDescription());
                    return newCategory;
                })
                .toList();

        return new PageImpl<>(list, categories.getPageable(), categories.getTotalElements());
    }

    CategoryEntity toEntity(Category category);

}
