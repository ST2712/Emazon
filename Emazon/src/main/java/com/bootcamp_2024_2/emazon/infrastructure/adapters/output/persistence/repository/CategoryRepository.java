package com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.repository;

import com.bootcamp_2024_2.emazon.infrastructure.adapters.output.persistence.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Page<CategoryEntity> findAll(Pageable pageable);

    @Query("SELECT c FROM CategoryEntity c ORDER BY c.name ASC")
    Page<CategoryEntity> findAllOrderedByNameAsc(Pageable pageable);

    @Query("SELECT c FROM CategoryEntity c ORDER BY c.name DESC")
    Page<CategoryEntity> findAllOrderedByNameDesc(Pageable pageable);

}