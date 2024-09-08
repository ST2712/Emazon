package com.bootcamp_2024_2.emazon.domain.api;

import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;

public interface IArticleServicePort {
    Article findById(Long id);
    CustomPage<Article> findAll(CustomPageable page);
    Article save(Article article);
}
