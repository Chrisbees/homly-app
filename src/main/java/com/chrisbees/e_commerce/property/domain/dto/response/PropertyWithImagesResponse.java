package com.chrisbees.e_commerce.property.domain.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import com.chrisbees.e_commerce.images.dto.ImageResponse;
import com.chrisbees.e_commerce.property.domain.Address;
import com.chrisbees.e_commerce.property.domain.PropertyDetails;

public record PropertyWithImagesResponse(
        String uuid,
        String name,
        String slug,
        String shortDesc,
        String longDesc,
        BigDecimal pricePerMonth,
        PropertyDetails propertyDetails,
        LocalDateTime availableFrom,
        String status,
        String categoryUuid,
        String categoryName,
        Address address,
        Instant createdAt,
        Instant updatedAt,
        List<ImageResponse> images
 ) {
} 