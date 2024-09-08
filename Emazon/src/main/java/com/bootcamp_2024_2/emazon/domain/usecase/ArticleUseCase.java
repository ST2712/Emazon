package com.bootcamp_2024_2.emazon.domain.usecase;

import com.bootcamp_2024_2.emazon.domain.api.IArticleServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.IArticlePersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.ArticleNotFoundException;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public Article findById(Long id) {
        return articlePersistencePort.findById(id).orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public CustomPage<Article> findAll(CustomPageable page) {
        return articlePersistencePort.findAll(page);
    }

    @Override
    public Article save(Article article) {
        return articlePersistencePort.save(article);
    }
}
