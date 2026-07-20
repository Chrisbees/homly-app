package com.chrisbees.e_commerce.category.domain.dto.mapper;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.category.domain.Category;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponse;
import com.chrisbees.e_commerce.category.domain.dto.CategoryResponseWithProperties;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertiesWithCategoryResponse;


@Component
public class CategoryResponseMapper {

    public CategoryResponse toDto(Category category){
        return new CategoryResponse(category.getUuid(),
        category.getName(),
        category.getDescription(),
        category.getCreatedAt(),
        category.getUpdatedAt());
    }

    public CategoryResponseWithProperties toDtoWithProducts(Category category){
        var products = category.getProducts().stream().map(t -> {
            return new PropertiesWithCategoryResponse(t.getUuid(),
             t.getName(), t.getSlug(), t.getShortDesc(), t.getPricePerMonth());
        }).toList();

        return new CategoryResponseWithProperties(category.getUuid(),
        category.getName(),
        category.getDescription(),
        category.getCreatedAt(),
        category.getUpdatedAt(),
        products);
    }
    
}
