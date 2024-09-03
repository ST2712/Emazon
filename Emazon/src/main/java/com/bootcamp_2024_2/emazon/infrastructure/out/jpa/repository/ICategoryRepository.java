package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository;

import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
