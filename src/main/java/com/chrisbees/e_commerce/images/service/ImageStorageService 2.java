package com.chrisbees.e_commerce.images.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chrisbees.e_commerce.images.dto.ImageResponse;


public interface ImageStorageService {
    void deleteFile(String fileName);
    List<ImageResponse> storeImageFile(List<MultipartFile> multipartFile, String propertyUuid) throws IOException;
}
