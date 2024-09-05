package com.bootcamp_2024_2.emazon.domain.usecase;

import com.bootcamp_2024_2.emazon.domain.api.IBrandServicePort;
import com.bootcamp_2024_2.emazon.domain.model.Brand;
import com.bootcamp_2024_2.emazon.domain.model.CustomPage;
import com.bootcamp_2024_2.emazon.domain.model.CustomPageable;
import com.bootcamp_2024_2.emazon.domain.spi.IBrandPersistencePort;
import com.bootcamp_2024_2.emazon.infrastructure.exception.BrandNotFoundException;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Brand findById(Long id) {
        return brandPersistencePort.findById(id).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public CustomPage<Brand> findAll(CustomPageable page) {
        return brandPersistencePort.findAll(page);
    }

    @Override
    public Brand save(Brand category) {
        return brandPersistencePort.save(category);
    }

    @Override
    public Brand update(Long id, Brand brand) {
        return brandPersistencePort.findById(id)
                .map(savedBrand -> {
                    savedBrand.setName(brand.getName());
                    savedBrand.setDescription(brand.getDescription());
                    return brandPersistencePort.save(savedBrand);
                })
                .orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(brandPersistencePort.findById(id).isEmpty()){
            throw new BrandNotFoundException();
        }
        brandPersistencePort.deleteById(id);
    }
}
