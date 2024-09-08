package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository;

import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
