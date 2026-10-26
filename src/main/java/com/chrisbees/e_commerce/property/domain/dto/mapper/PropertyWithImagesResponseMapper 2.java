package com.chrisbees.e_commerce.property.domain.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.images.dto.ImageResponse;
import com.chrisbees.e_commerce.property.domain.Property;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyWithImagesResponse;

@Component
public class PropertyWithImagesResponseMapper {


    public PropertyWithImagesResponse toDto(Property property, List<ImageResponse> images) {
        String categoryId = null;
        String categoryName = null;
        if (property.getCategory() != null) {
            categoryId = property.getCategory().getUuid();
            categoryName = property.getCategory().getName();
        }
       return new PropertyWithImagesResponse(
                property.getUuid(),
                property.getName(),
                property.getSlug(),
                property.getShortDesc(),
                property.getLongDesc(),
                property.getPricePerMonth(),
                property.getPropertyDetails(),
                property.getAvailableFrom(),
                property.getStatus().toString(),
                categoryId,
                categoryName,
                property.getAddress(),
                property.getCreatedAt(),
                property.getUpdatedAt(),
                images
        );
    }
    
}
