package com.chrisbees.e_commerce.images.dto;


public record ImageResponse(
     String originalName,
     String storedName,
     String mimeType,
     String storagePath

) {
    
}
