package com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.repository;

import com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
