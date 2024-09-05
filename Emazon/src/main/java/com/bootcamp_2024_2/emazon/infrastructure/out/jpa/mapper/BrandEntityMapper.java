package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

    Brand toBrand(BrandEntity brandEntity);
    default List<Brand> toBrands(List<BrandEntity> brandEntities) {
        if (brandEntities == null) {
            return List.of();
        }

        return brandEntities.stream()
                .map(this::toBrand)
                .toList();
    }
    BrandEntity toEntity(Brand brand);
}
