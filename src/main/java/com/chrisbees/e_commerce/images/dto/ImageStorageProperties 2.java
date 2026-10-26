package com.chrisbees.e_commerce.images.dto;

import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.image-storage")
public record ImageStorageProperties(
        String basePath,
        Map<String, Set<String>> allowedMimeTypes) {
    public ImageStorageProperties() {
        this("./images",
                Map.of("image/jpeg", Set.of("jpeg", "jpg"),
                        "image/png", Set.of("png")));
    };
}
