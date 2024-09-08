package com.bootcamp_2024_2.emazon.application.handler;

import com.bootcamp_2024_2.emazon.application.dto.request.ArticleRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleHandler {
    ArticleResponse findById(Long id);
    Page<ArticleResponse> findAll(Pageable page);
    ArticleResponse save(ArticleRequest article);
}
