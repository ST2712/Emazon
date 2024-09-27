package com.bootcamp_2024_2.emazon.application.mapper.response;

import com.bootcamp_2024_2.emazon.application.dto.response.ArticleResponse;
import com.bootcamp_2024_2.emazon.domain.model.Article;
import com.bootcamp_2024_2.emazon.domain.model.Category;
import com.bootcamp_2024_2.emazon.domain.model.CategorySummary;
import com.bootcamp_2024_2.emazon.infrastructure.exception.ArticleNotFoundException;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleResponseMapperImpl implements ArticleResponseMapper {

    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;

    public ArticleResponseMapperImpl(IBrandRepository brandRepository, ICategoryRepository categoryRepository,
                                      BrandEntityMapper brandEntityMapper, CategoryEntityMapper categoryEntityMapper) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.brandEntityMapper = brandEntityMapper;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public ArticleResponse toResponse(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleResponse articleResponse = new ArticleResponse();

        articleResponse.setId( article.getId() );
        articleResponse.setName( article.getName() );
        articleResponse.setDescription( article.getDescription() );
        articleResponse.setQuantity( article.getQuantity() );
        articleResponse.setPrice( article.getPrice() );

        BrandEntity brandEntity = brandRepository.findById(article.getBrandId())
                        .orElseThrow(ArticleNotFoundException::new);
        articleResponse.setBrand(brandEntityMapper.toBrand(brandEntity));

        List<CategoryEntity> categoryEntityList = categoryRepository.findAllById(article.getCategoriesIds());
        List<Category> categories = categoryEntityMapper.toCategories(categoryEntityList);
        List<CategorySummary> categorySummaries = categories.stream().map(CategorySummary::new).toList();
        articleResponse.setCategories(categorySummaries);

        return articleResponse;
    }

    protected CategorySummary categoryToCategorySummary(Category category) {
        if ( category == null ) {
            return null;
        }

        CategorySummary categorySummary = new CategorySummary();

        categorySummary.setId( category.getId() );
        categorySummary.setName( category.getName() );

        return categorySummary;
    }

    protected List<CategorySummary> categoryListToCategorySummaryList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategorySummary> list1 = new ArrayList<CategorySummary>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategorySummary( category ) );
        }

        return list1;
    }
}
