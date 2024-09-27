package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.infrastructure.exception.BrandNotFoundException;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ArticleEntityMapperImpl implements ArticleEntityMapper {

    private final ICategoryRepository categoryRepository;
    private final IBrandRepository brandRepository;

    public ArticleEntityMapperImpl(ICategoryRepository categoryRepository, IBrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Article toArticle(ArticleEntity articleEntity) {
        if ( articleEntity == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( articleEntity.getId() );
        article.setName( articleEntity.getName() );
        article.setDescription( articleEntity.getDescription() );
        article.setQuantity( articleEntity.getQuantity() );
        article.setPrice( articleEntity.getPrice() );
        article.setBrandId( articleEntity.getBrand().getId() );
        article.setCategoriesIds( articleEntity.getCategories().stream().map(CategoryEntity::getId).toList() );

        return article;
    }

    @Override
    public ArticleEntity toEntity(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setId( article.getId() );
        articleEntity.setName( article.getName() );
        articleEntity.setDescription( article.getDescription() );
        articleEntity.setQuantity( article.getQuantity() );
        articleEntity.setPrice( article.getPrice() );
        articleEntity.setBrand(brandRepository.findById(article.getBrandId())
                .orElseThrow(BrandNotFoundException::new));
        articleEntity.setCategories(categoryRepository.findAllById(article.getCategoriesIds()));

        return articleEntity;
    }
}
