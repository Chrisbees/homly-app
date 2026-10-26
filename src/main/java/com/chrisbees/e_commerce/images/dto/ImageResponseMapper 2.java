package com.chrisbees.e_commerce.images.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chrisbees.e_commerce.images.domain.PropertyImage;

@Component
public class ImageResponseMapper {
    
    public List<ImageResponse> toDto(List<PropertyImage> image){

        List<ImageResponse> imageResponses = new ArrayList<>();
        image.stream()
        .forEach(img -> {
            imageResponses.add(new ImageResponse(img.getOriginalName(), img.getStoredName(),
         img.getMimeType(), img.getStoragePath()));
        });

        return imageResponses;

    }
        
}
