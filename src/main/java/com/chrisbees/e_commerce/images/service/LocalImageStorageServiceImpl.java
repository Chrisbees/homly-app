package com.chrisbees.e_commerce.images.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chrisbees.e_commerce.images.domain.PropertyImage;
import com.chrisbees.e_commerce.images.dto.ImageResponse;
import com.chrisbees.e_commerce.images.dto.ImageResponseMapper;
import com.chrisbees.e_commerce.images.dto.ImageStorageProperties;
import com.chrisbees.e_commerce.images.exception.ImageFileNotFoundException;
import com.chrisbees.e_commerce.images.exception.MimeTypeError;
import com.chrisbees.e_commerce.images.repository.ImageRepository;
import com.chrisbees.e_commerce.property.exception.PropertyNotFoundException;
import com.chrisbees.e_commerce.property.repositiory.PropertyRepository;

import jakarta.transaction.Transactional;

@Service
public class LocalImageStorageServiceImpl implements ImageStorageService {

    private final Tika tika = new Tika();
    private final ImageStorageProperties imageStorageProperties;
    private final ImageRepository imageRepository;
    private final PropertyRepository propertyRepository;
    private final ImageResponseMapper imageResponseMapper;
    private final Path rootPath;

    public LocalImageStorageServiceImpl(ImageStorageProperties imageStorageProperties,
            ImageRepository imageRepository, PropertyRepository propertyRepository,
            ImageResponseMapper imageResponseMapper) {
        this.imageStorageProperties = imageStorageProperties;
        this.imageRepository = imageRepository;
        this.propertyRepository = propertyRepository;
        this.imageResponseMapper = imageResponseMapper;
        this.rootPath = Paths.get(imageStorageProperties.basePath());
    }

    @Override
    @Transactional
    public List<ImageResponse> storeImageFile(List<MultipartFile> multipartFile, String propertyUuid)
            throws IOException {
        var property = propertyRepository.findByUuidAndDeletedFalse(propertyUuid)
                .orElseThrow(() -> new PropertyNotFoundException("Property Does not Exists"));
        LocalDate today = LocalDate.now();
        Path dateDirectory = rootPath.resolve(
                today.getYear() + File.separator +
                        String.format("%02d", today.getMonthValue()) + File.separator +
                        String.format("%02d", today.getDayOfMonth()));

        Files.createDirectories(dateDirectory);
        List<PropertyImage> propertyImageList = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            PropertyImage propertyImage = new PropertyImage();
            InputStream inputStream = validateImage(file);
            var ext = getFileExtension(file.getOriginalFilename());
            var storedName = UUID.randomUUID() + (ext.isEmpty() ? "" : "." + ext);
            Path filePath = dateDirectory.resolve(storedName);
            try (inputStream;
                    OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW)) {
                StreamUtils.copy(inputStream, outputStream);
            }
            String storagePath = rootPath.relativize(filePath).toString();
            propertyImage.setStoragePath(storagePath);
            propertyImage.setMimeType(file.getContentType());
            propertyImage.setOriginalName(file.getOriginalFilename());
            propertyImage.setStoredName(storedName);
            propertyImage.setProperty(property);
            propertyImageList.add(propertyImage);
        }

        var image = imageRepository.saveAll(propertyImageList);
        return imageResponseMapper.toDto(image);
    }

    private InputStream validateImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new ImageFileNotFoundException("File is empty");
        }
        InputStream inputStream = multipartFile.getInputStream();
        String detect = tika.detect(inputStream);
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        Set<String> allowedTypes = imageStorageProperties.allowedMimeTypes().get(detect);
        if (detect == null || allowedTypes == null || !allowedTypes.contains(fileExtension)) {
            throw new MimeTypeError("Invalid Mime Type: " + detect + " file extension: " + 
            " original file name: " + originalFilename);

        }
        else 
            return inputStream;

    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return "";
        }
        int lastDot = fileName.lastIndexOf(".");
        return lastDot == -1 ? "" : fileName.substring(lastDot + 1);
    }

    @Override
    public void deleteFile(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFile'");
    }

}
