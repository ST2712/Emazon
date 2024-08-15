package com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.repository;

import com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c ORDER BY c.name ASC")
    List<CategoryEntity> findAllOrderedByNameAsc();

    @Query("SELECT c FROM CategoryEntity c ORDER BY c.name DESC")
    List<CategoryEntity> findAllOrderedByNameDesc();

}