package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.adapter;

import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.IArticlePersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.NoDataFoundException;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.ArticleEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Optional<Article> findById(Long id) {
        Optional<ArticleEntity> articleEntityOptional = articleRepository.findById(id);
        return articleEntityOptional.map(articleEntityMapper::toArticle);
    }

    @Override
    public CustomPage<Article> findAll(CustomPageable pageable) {
        Sort sort = pageable.getSortOrder().equalsIgnoreCase("desc") ?
                Sort.by(pageable.getSortBy()).descending() :
                Sort.by(pageable.getSortBy()).ascending();

        Pageable pageableJpa = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageableJpa);

        List<Article> articles = articleEntityMapper.toArticles(articleEntityPage.getContent());
        CustomPage<Article> customPage = new CustomPage<>();
        customPage.setContent(articles);
        customPage.setTotalElements(articleEntityPage.getTotalElements());
        customPage.setPageNumber(pageable.getPageNumber());
        customPage.setPageSize(pageable.getPageSize());
        return customPage;
    }

    @Override
    public Article save(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);

        for(CategoryEntity categoryEntity : articleEntity.getCategories()) {
            if (categoryEntity.getDescription() == null || categoryEntity.getDescription().isBlank()) {
                throw new NoDataFoundException("Category cannot be empty or null");
            }
        }

        ArticleEntity savedArticleEntity = articleRepository.save(articleEntity);
        return articleEntityMapper.toArticle(savedArticleEntity);
    }
}
