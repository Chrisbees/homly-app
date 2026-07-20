package com.chrisbees.e_commerce.category.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chrisbees.e_commerce.category.domain.dto.CategoryRequest;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponse;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponseWithProperties;
import com.chrisbees.e_commerce.category.domain.dto.mapper.CategoryRequestMapper;
import com.chrisbees.e_commerce.category.domain.dto.mapper.CategoryResponseMapper;
import com.chrisbees.e_commerce.category.repositiory.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRequestMapper categoryRequestMapper, CategoryResponseMapper categoryResponseMapper,
        CategoryRepository categoryRepository
    ){
        this.categoryRequestMapper = categoryRequestMapper;
        this.categoryResponseMapper = categoryResponseMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        var category = categoryRequestMapper.fromDto(categoryRequest);
        category.setUuid(UUID.randomUUID().toString());
        var createdcategory = categoryRepository.save(category);
        return categoryResponseMapper.toDto(createdcategory);
    }

    @Override
    public CategoryResponse getCategoryByUUID(String uuid) {
        var category = categoryRepository.findByUuid(uuid).get();
        return categoryResponseMapper.toDto(category);
    }

    @Override
    public List<CategoryResponse> returnAllCategories() {
        return Arrays.asList(new CategoryResponse());
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(String uuid, CategoryRequest categoryRequest) {
        var category = categoryRepository.findByUuid(uuid).get();
        System.out.println(category);
        if (categoryRequest.name() != null) {
            category.setName(categoryRequest.name());
        }

        if (categoryRequest.desc() != null) {
            category.setDescription(categoryRequest.desc());
        }
        var categoryUpdated = categoryRepository.save(category);
        return categoryResponseMapper.toDto(categoryUpdated);
    }

    @Override
    public void deleteCategory(String uuid) {
        
    }

    @Override
    public CategoryResponseWithProperties getCategoryWithProducts(String uuid) {
        var category = categoryRepository.findByUuid(uuid).get();
        var categoryWithProducts = categoryResponseMapper.toDtoWithProducts(category);
        return categoryWithProducts;
    }
    
}
