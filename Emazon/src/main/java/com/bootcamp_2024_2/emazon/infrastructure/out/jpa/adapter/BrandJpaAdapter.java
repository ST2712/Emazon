package com.bootcamp_2024_2.emazon.infrastructure.out.jpa.adapter;

import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.IBrandPersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.NoDataFoundException;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.bootcamp_2024_2.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public Optional<Brand> findById(Long id) {
        Optional<BrandEntity> brandEntityOptional = brandRepository.findById(id);
        return brandEntityOptional.map(brandEntityMapper::toBrand);
    }

    @Override
    public CustomPage<Brand> findAll(CustomPageable pageable) {
        Sort sort = pageable.getSortOrder().equalsIgnoreCase("desc") ?
                Sort.by(pageable.getSortBy()).descending() :
                Sort.by(pageable.getSortBy()).ascending();

        Pageable pageableJpa = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageableJpa);

        if (brandEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<Brand> brands = brandEntityMapper.toBrands(brandEntityPage.getContent());
        CustomPage<Brand> customPage = new CustomPage<>();
        customPage.setContent(brands);
        customPage.setTotalElements(brandEntityPage.getTotalElements());
        customPage.setPageNumber(pageable.getPageNumber());
        customPage.setPageSize(pageable.getPageSize());
        return customPage;
    }

    @Override
    public Brand save(Brand brand) {
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        BrandEntity savedBrandEntity = brandRepository.save(brandEntity);
        return brandEntityMapper.toBrand(savedBrandEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new NoDataFoundException();
        }
        brandRepository.deleteById(id);
    }
}
