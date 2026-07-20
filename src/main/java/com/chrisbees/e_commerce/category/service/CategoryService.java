package com.chrisbees.e_commerce.category.service;

import java.util.List;

import com.chrisbees.e_commerce.category.domain.dto.CategoryRequest;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponse;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponseWithProperties;

public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategoryByUUID (String uuid);
    List<CategoryResponse> returnAllCategories();
    CategoryResponse updateCategory(String uuid, CategoryRequest productRequest);
    void deleteCategory(String uuid);
    CategoryResponseWithProperties getCategoryWithProducts(String uuid);
}
