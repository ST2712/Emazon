package com.bootcamp_2024_2.emazon.application.mapper.response;

import com.bootcamp_2024_2.emazon.application.dto.response.ArticleResponse;
import com.bootcamp_2024_2.emazon.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleResponseMapper {
    ArticleResponse toResponse(Article article);
    default Page<ArticleResponse> toResponseList(Page<Article> articles) {
        if ( articles == null ) {
            return null;
        }

        List<ArticleResponse> list = articles.getContent().stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(list, articles.getPageable(), articles.getTotalElements());
    }
    default List<ArticleResponse> toResponseList(List<Article> articles) {
        if (articles == null) {
            return Collections.emptyList();
        }

        return articles.stream()
                .map(this::toResponse)
                .toList();
    }
}
