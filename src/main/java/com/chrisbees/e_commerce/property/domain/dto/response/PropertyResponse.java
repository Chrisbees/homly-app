package com.chrisbees.e_commerce.property.domain.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.chrisbees.e_commerce.images.domain.PropertyImage;
import com.chrisbees.e_commerce.property.domain.Address;

public record PropertyResponse(
    
        String uuid,
        String name,
        String shortDesc,
        BigDecimal pricePerMonth,
        String status,
        Address address,
        Instant createdAt,
        Instant updatedAt,
        List<PropertyImage> images
) {



}
