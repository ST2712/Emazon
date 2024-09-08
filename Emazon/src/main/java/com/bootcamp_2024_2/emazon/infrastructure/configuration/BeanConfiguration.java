package com.bootcamp_2024_2.emazon.infrastructure.configuration;

import com.bootcamp_2024_2.emazon.domain.api.IArticleServicePort;
import com.bootcamp_2024_2.emazon.domain.api.IBrandServicePort;
import com.bootcamp_2024_2.emazon.domain.api.ICategoryServicePort;
import com.bootcamp_2024_2.emazon.domain.spi.IArticlePersistencePort;
import com.bootcamp_2024_2.emazon.domain.spi.IBrandPersistencePort;
import com.bootcamp_2024_2.emazon.domain.spi.ICategoryPersistencePort;
import com.bootcamp_2024_2.emazon.domain.usecase.ArticleUseCase;
import com.bootcamp_2024_2.emazon.domain.usecase.BrandUseCase;
import com.bootcamp_2024_2.emazon.domain.usecase.CategoryUseCase;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.adapter.ArticleJpaAdapter;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.adapter.BrandJpaAdapter;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.ArticleEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.IArticleRepository;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;


    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleUseCase(articlePersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }


}
