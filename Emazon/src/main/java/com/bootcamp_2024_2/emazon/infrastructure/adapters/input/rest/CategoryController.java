package com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest;

import com.bootcamp_2024_2.emazon.application.ports.input.CategoryServicePort;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.mapper.CategoryRestMapper;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.request.CategoryCreateRequest;
import com.bootcamp_2024_2.emazon.infrastructure.adapters.input.rest.model.response.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServicePort servicePort;
    private final CategoryRestMapper restMapper;

    @GetMapping(path = "/v1/categories")
    public List<CategoryResponse> getCategories() {
        return restMapper.toCategoryResponseList(servicePort.findAll());
    }

    @GetMapping(path = "/v1/category/{id}")
    public CategoryResponse getCategory(@PathVariable Long id) {
        return restMapper.toCategoryResponse(servicePort.findById(id));
    }

    @GetMapping(path = "/v1/categoriesByNameAsc")
    public List<CategoryResponse> getCategoriesByNameAsc() {
        return restMapper.toCategoryResponseList(servicePort.findAllOrderedByNameAsc());
    }

    @GetMapping(path = "/v1/categoriesByNameDesc")
    public List<CategoryResponse> getCategoriesByNameDesc() {
        return restMapper.toCategoryResponseList(servicePort.findAllOrderedByNameDesc());
    }

    @PostMapping(path = "/v1/createCategory")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toCategoryResponse(
                        servicePort.save(restMapper.toCategory(request))));
    }

    @PutMapping(path = "/v1/updateCategory/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequest request){
        return restMapper.toCategoryResponse(
                        servicePort.update(id, restMapper.toCategory(request)));
    }

    @DeleteMapping(path = "/v1/deleteCategory/{id}")
    public void deleteCategory(@PathVariable Long id){
        servicePort.deleteById(id);
    }
}
