package com.bootcamp_2024_2.emazon.application.mapper.request;

import com.bootcamp_2024_2.emazon.application.dto.request.ArticleRequest;
import com.bootcamp_2024_2.emazon.application.dto.request.CategoryRequest;
import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {
    Article toArticle(ArticleRequest articleRequest);
}
