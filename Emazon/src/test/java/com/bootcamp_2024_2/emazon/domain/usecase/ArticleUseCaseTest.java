package com.bootcamp_2024_2.emazon.domain.usecase;

import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.IArticlePersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.ArticleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    private Article article;
    private CustomPageable pageable;
    private CustomPage<Article> customPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        article = new Article(1L, "Smartphone", "High-end smartphone", 50, 999.99, Collections.emptyList());
        pageable = new CustomPageable(0, 10, "name", "asc");
        customPage = new CustomPage<>(Collections.singletonList(article), 0, 10, 1, 1, true);
    }

    @Test
    void findById_ArticleExists_ReturnsArticle() {
        when(articlePersistencePort.findById(1L)).thenReturn(Optional.of(article));

        Article result = articleUseCase.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Smartphone", result.getName());
        verify(articlePersistencePort, times(1)).findById(1L);
    }

    @Test
    void findById_ArticleDoesNotExist_ThrowsException() {
        when(articlePersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class, () -> articleUseCase.findById(1L));
        verify(articlePersistencePort, times(1)).findById(1L);
    }

    @Test
    void findAll_ReturnsCustomPageOfArticles() {
        when(articlePersistencePort.findAll(pageable)).thenReturn(customPage);

        CustomPage<Article> result = articleUseCase.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(1L, result.getTotalElements());
        assertEquals(article, result.getContent().get(0));
        verify(articlePersistencePort, times(1)).findAll(pageable);
    }

    @Test
    void save_ArticleIsSaved_ReturnsSavedArticle() {
        when(articlePersistencePort.save(any(Article.class))).thenReturn(article);

        Article result = articleUseCase.save(article);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Smartphone", result.getName());
        verify(articlePersistencePort, times(1)).save(article);
    }
}
