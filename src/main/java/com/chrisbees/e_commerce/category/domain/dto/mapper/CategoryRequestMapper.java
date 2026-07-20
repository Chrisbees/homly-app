package com.chrisbees.e_commerce.category.domain.dto.mapper;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.category.domain.Category;
import com.chrisbees.e_commerce.category.domain.dto.CategoryRequest;

@Component
public class CategoryRequestMapper {

    public Category fromDto(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.name().toLowerCase());
        category.setDescription(categoryRequest.desc());
        return category;
    }
    
}
