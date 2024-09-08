package com.bootcamp_2024_2.emazon.domain.spi;

import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;

import java.util.Optional;

public interface IArticlePersistencePort {
    Optional<Article> findById(Long id);
    CustomPage<Article> findAll(CustomPageable page);
    Article save(Article article);
}
