package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository;

import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
}
