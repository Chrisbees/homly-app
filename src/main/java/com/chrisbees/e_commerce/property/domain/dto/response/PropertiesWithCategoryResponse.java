package com.chrisbees.e_commerce.property.domain.dto.response;

import java.math.BigDecimal;

public record PropertiesWithCategoryResponse(
    String uuid,
    String name,
    String slug,
    String shortDesc,
    BigDecimal price
) {



}
