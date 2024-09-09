package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.ArticleRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.ArticleResponse;
import com.bootcamp_2024_2.emazon.application.mapper.request.ArticleRequestMapper;
import com.bootcamp_2024_2.emazon.application.mapper.response.ArticleResponseMapper;
import com.bootcamp_2024_2.emazon.domain.api.IArticleServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler{

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;

    @Override
    public ArticleResponse findById(Long id) {
        Article article = articleServicePort.findById(id);
        return articleResponseMapper.toResponse(article);
    }

    @Override
    public Page<ArticleResponse> findAll(Pageable pageable) {
        String sortBy = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getProperty).orElse("name");
        String sortOrder = pageable.getSort().stream().findFirst()
                .map(Sort.Order::getDirection).orElse(Sort.Direction.ASC).name().toLowerCase();

        sortBy = adjustSortByField(sortBy);

        CustomPageable customPageable = new CustomPageable(pageable.getPageNumber(), pageable.getPageSize(), sortBy, sortOrder);

        CustomPage<Article> customPage = articleServicePort.findAll(customPageable);

        List<ArticleResponse> articleResponses = articleResponseMapper.toResponseList(customPage.getContent());

        return new PageImpl<>(articleResponses, pageable, customPage.getTotalElements());
    }

    private String adjustSortByField(String sortBy) {
        if ("brand".equalsIgnoreCase(sortBy)) {
            return "brand.name";
        } else if ("category".equalsIgnoreCase(sortBy)) {
            return "categories.name";
        }
        return sortBy;
    }

    @Override
    public ArticleResponse save(ArticleRequest article) {
        return articleResponseMapper.toResponse(articleServicePort.save(articleRequestMapper.toArticle(article)));
    }
}
