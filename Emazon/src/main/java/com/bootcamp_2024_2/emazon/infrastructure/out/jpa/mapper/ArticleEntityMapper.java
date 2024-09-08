package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper;

import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {
    Article toArticle(ArticleEntity articleEntity);
    default List<Article> toArticles(List<ArticleEntity> articleEntities) {
        if (articleEntities == null) {
            return List.of();
        }

        return articleEntities.stream()
                .map(this::toArticle)
                .toList();
    }
    ArticleEntity toEntity(Article article);
}
