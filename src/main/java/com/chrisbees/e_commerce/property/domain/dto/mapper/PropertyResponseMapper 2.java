package com.chrisbees.e_commerce.property.domain.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.images.domain.PropertyImage;
import com.chrisbees.e_commerce.property.domain.Property;
import com.chrisbees.e_commerce.property.domain.dto.response.PropertyResponse;


@Component
public class PropertyResponseMapper {

    public PropertyResponse toDto(Property property, List<PropertyImage> images){
        return new PropertyResponse(
                property.getUuid(),
                property.getName(),
                property.getShortDesc(),
                property.getPricePerMonth(),
                property.getStatus().toString(),
                property.getAddress(),
                property.getCreatedAt(),
                property.getUpdatedAt(),
                images

        );
    }
    
}
