package com.chrisbees.e_commerce.category.domain.dto;

import java.time.Instant;
import java.util.List;

import com.chrisbees.e_commerce.property.domain.dto.response.PropertiesWithCategoryResponse;


public record CategoryResponseWithProperties(
    String uuid,
    String name,
    String desc,
    Instant createdAt,
    Instant updatedAt,
    List<PropertiesWithCategoryResponse> product
) {

}
